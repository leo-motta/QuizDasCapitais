package com.example.quizdascapitais

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.quizdascapitais.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Databinding: inflar o layout
        val binding: FragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        //Executa quando o botao JOGAR é acionado
        binding.button.setOnClickListener(){
            it.findNavController().navigate(R.id.action_homeFragment_to_gameFragment)
        }
        //Executa quando o botao SOBRE é acionado
        binding.button5.setOnClickListener(){
            it.findNavController().navigate(R.id.action_homeFragment_to_aboutFragment)
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,requireView().findNavController())
                || super.onOptionsItemSelected(item)

    }
}