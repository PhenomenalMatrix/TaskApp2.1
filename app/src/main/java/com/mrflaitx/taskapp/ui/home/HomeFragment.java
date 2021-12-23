package com.mrflaitx.taskapp.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.mrflaitx.taskapp.App;
import com.mrflaitx.taskapp.R;
import com.mrflaitx.taskapp.databinding.FragmentHomeBinding;
import com.mrflaitx.taskapp.models.User;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements TaskAdapter.OnItemClick {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private final TaskAdapter adapter = new TaskAdapter();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListeners();
        setFragmentListener();

        App.database.userDao().getAllUsers().observe(getViewLifecycleOwner(),new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                adapter.setList(users);
                Log.e("TAG", "Live data worker: ");
            }
        });

        initRv();
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        adapter.setList(App.database.userDao().getAllUsersList());
//    }

    private void initRv() {
        adapter.setListener(this);
        binding.taskRv.setAdapter(adapter);
    }


    private void setFragmentListener() {
        getParentFragmentManager().setFragmentResultListener("key", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                User user = (User) result.getSerializable("user");
                App.database.userDao().addUser(user);
            }
        });
    }

    private void initListeners() {
        binding.actionBtn.setOnClickListener(view -> {
            openFragment();
        });
    }

    private void openFragment() {
        NavController navController = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.formFragment);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(String txt) {
        Toast.makeText(requireContext(),txt,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLongClick(int position) {
        Log.e("TAG", "position: "+ position );
        new AlertDialog.Builder(requireContext())
                .setMessage("Внимание")
                .setIcon(R.drawable.ic_launcher_foreground)
                .setTitle("Удалить запись ?")
                .setNegativeButton("Нет", null)
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        adapter.removeItem(position);
                        binding.taskRv.setAdapter(adapter);
                    }
                }).show();
    }
}