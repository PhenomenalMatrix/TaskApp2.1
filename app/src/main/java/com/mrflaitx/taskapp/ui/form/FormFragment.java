package com.mrflaitx.taskapp.ui.form;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.mrflaitx.taskapp.MainActivity;
import com.mrflaitx.taskapp.R;
import com.mrflaitx.taskapp.databinding.FragmentFormBinding;
import com.mrflaitx.taskapp.models.User;
import com.mrflaitx.taskapp.ui.home.HomeFragment;

import java.io.Serializable;

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
        playAnimations();
        initListeners();
    }

    private void playAnimations() {
//        binding.saveBtn.animate().alpha(0f).start();
//        binding.inputLayout.animate().alpha(0f).start();
//
//        binding.saveBtn.animate().alpha(1f).setDuration(500);
//        binding.inputLayout.animate().alpha(1f).setDuration(500);
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
        String name = binding.nameEt.getText().toString();
        String surname = binding.surnameEt.getText().toString();
        User user = new User(name,surname);
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        getParentFragmentManager().setFragmentResult("key",bundle);
    }
}