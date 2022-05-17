package com.example.composition.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.composition.R
import com.example.composition.databinding.FragmentChooseLevelBinding
import com.example.composition.domain.entity.Level

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ChooseLevelFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChooseLevelFragment : Fragment() {

    private var _binding: FragmentChooseLevelBinding? = null
    private val binding: FragmentChooseLevelBinding
    get() = _binding ?: throw RuntimeException("FragmentChooseLevel == null")

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
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
        requireActivity().supportFragmentManager.beginTransaction()
            .addToBackStack(GameFragment.NAME)
            .replace(R.id.main_container, GameFragment.newInstance(level))
            .commit()
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