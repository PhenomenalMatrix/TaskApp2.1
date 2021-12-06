package com.mrflaitx.taskapp.ui.form;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mrflaitx.taskapp.R;
import com.mrflaitx.taskapp.databinding.FragmentFormBinding;
import com.mrflaitx.taskapp.ui.home.HomeFragment;

public class FormFragment extends Fragment {

    private FragmentFormBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFormBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListeners();
    }

    private void initListeners() {
        binding.saveBtn.setOnClickListener(view -> {
            save();
            close();
        });
    }

    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }

    private void save() {
        String text = binding.taskEt.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString("text",text);
        getParentFragmentManager().setFragmentResult("key",bundle);
    }
}