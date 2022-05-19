package com.example.composition.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.composition.R
import com.example.composition.databinding.FragmentChooseLevelBinding
import com.example.composition.domain.entity.Level

/**
 * A simple [Fragment] subclass.
 * Use the [ChooseLevelFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChooseLevelFragment : Fragment() {

    private var _binding: FragmentChooseLevelBinding? = null
    private val binding: FragmentChooseLevelBinding
    get() = _binding ?: throw RuntimeException("FragmentChooseLevel == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChooseLevelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*binding.btnLevelEasy.setOnClickListener(this)
        binding.btnLevelMedium.setOnClickListener(this)
        binding.btnLevelHard.setOnClickListener(this)*/
        binding.btnLevelTest.setOnClickListener{
            launchGameFragment(Level.TEST)
        }
        binding.btnLevelEasy.setOnClickListener {
            launchGameFragment(Level.EASY)
        }
        binding.btnLevelMedium.setOnClickListener {
            launchGameFragment(Level.NORMAL)
        }
        binding.btnLevelHard.setOnClickListener {
            launchGameFragment(Level.HARD)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding == null
    }

    private fun launchGameFragment(level: Level) {
       /* requireActivity().supportFragmentManager.beginTransaction()
            .addToBackStack(GameFragment.NAME)
            .replace(R.id.main_container, GameFragment.newInstance(level))
            .commit()*/
        val args = Bundle().apply {
            arguments.apply {
                putParcelable(GameFragment.KEY_LEVEL, level)
            }
        }
        findNavController().navigate(R.id.action_chooseLevelFragment_to_gameFragment, args)
    }

    /*override fun onClick(v: View?) {
        when(v) {
            binding.btnLevelEasy -> launchGameFragment(GameFragment.newInstance(Level.EASY))
            binding.btnLevelMedium -> launchGameFragment(GameFragment.newInstance(Level.NORMAL))
            binding.btnLevelHard -> launchGameFragment(GameFragment.newInstance(Level.HARD))
        }
    }*/

    companion object {
        const val NAME = "name"

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = ChooseLevelFragment()
    }
}