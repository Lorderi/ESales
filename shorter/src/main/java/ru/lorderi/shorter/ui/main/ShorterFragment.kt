package ru.lorderi.shorter.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.lorderi.shorter.databinding.FragmentShorterBinding


class ShorterFragment : Fragment() {

    companion object {
        fun newInstance() = ShorterFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentShorterBinding.inflate(inflater, container, false)

        return binding.root
    }
}