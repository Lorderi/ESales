package ru.lorderi.effectivesales.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.lorderi.effectivesales.databinding.FragmentHotelsBinding


class HotelsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHotelsBinding.inflate(inflater, container, false)

        return binding.root
    }

}