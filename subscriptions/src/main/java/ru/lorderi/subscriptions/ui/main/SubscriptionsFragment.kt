package ru.lorderi.subscriptions.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.lorderi.subscriptions.databinding.FragmentSubscriptionsBinding


class SubscriptionsFragment : Fragment() {
    companion object {
        fun newInstance() = SubscriptionsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSubscriptionsBinding.inflate(inflater, container, false)

        return binding.root
    }
}