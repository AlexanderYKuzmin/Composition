package com.example.composition.presentation

import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.composition.R
import com.example.composition.databinding.FragmentGameBinding
import com.example.composition.domain.entity.GameResult
import com.example.composition.domain.entity.GameSettings
import com.example.composition.domain.entity.Level
import com.example.composition.domain.entity.Question
import java.lang.RuntimeException

class GameFragment : Fragment() {


    private lateinit var level: Level
    private val viewModelFactory by lazy {
        GameViewModelFactory(requireActivity().application, level)
    }

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            viewModelFactory
        )[GameViewModel::class.java]
    }

    private val tvOptions by lazy {
        mutableListOf<TextView>().apply {
            add(binding.tvOption1)
            add(binding.tvOption2)
            add(binding.tvOption3)
            add(binding.tvOption4)
            add(binding.tvOption5)
            add(binding.tvOption6)
        }
    }

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
    get() = _binding ?: throw RuntimeException("GameFragment == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.question.observe(viewLifecycleOwner, Observer {
            setQuestionAnswerValues(it)
        })

        viewModel.formattedTime.observe(viewLifecycleOwner, Observer {
            setTimeValue(it)
        })

        viewModel.percentOfRightAnswers.observe(viewLifecycleOwner, Observer {
            setProgressBarValue(it)
        })

        viewModel.enoughCountOfRightAnswers.observe(viewLifecycleOwner, Observer {
            setProgressTextColor(it)
        })

        viewModel.enoughPercentOfRightAnswers.observe(viewLifecycleOwner, Observer {
            setProgressBarColor(it)
        })

        viewModel.minPercent.observe(viewLifecycleOwner, Observer {
            setSecondaryProgressBarValue(it)
        })

        viewModel.gameResult.observe(viewLifecycleOwner, Observer {
            launchGameFinishedFragment(it)
        })

        viewModel.progressAnswers.observe(viewLifecycleOwner, Observer {
            setProgressAnswers(it)
        })

        setUpListeners()
        //viewModel.startGame(level)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding == null
    }

    private fun parseArgs() {
        requireArguments().getParcelable<Level>(KEY_LEVEL)?.let {
            level = it
        }
    }

    private fun launchGameFinishedFragment(gameResult: GameResult) {
        /*requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, GameFinishedFragment.newInstance(gameResult))
            .addToBackStack(null)
            .commit()*/
        val args = Bundle().apply {
            arguments.apply {
                putParcelable(GameFinishedFragment.GAME_RESULT, gameResult)
            }
        }
        findNavController().navigate(R.id.action_gameFragment_to_gameFinishedFragment, args)
    }

    private fun setQuestionAnswerValues(question: Question) {
        binding.tvSum.text = question.sum.toString()
        binding.tvLeftNumber.text = question.visibleNumber.toString()
        for (i in question.options.indices) {
            tvOptions[i].text = question.options[i].toString()
        }
    }

    private fun setTimeValue(formattedTime: String) {
        binding.tvTimer.text = formattedTime
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun setProgressBarValue(progress: Int) {
        binding.progressBar.setProgress(progress, true)
    }

    private fun setProgressTextColor(isRightAnswersEnough: Boolean) {
        val colorResId = if (isRightAnswersEnough) {
            android.R.color.holo_green_light
        } else {
            android.R.color.holo_red_light
        }
        val color = ContextCompat.getColor(requireContext(), colorResId)
        binding.tvAnswersProgress.setTextColor(color)
    }

    private fun setProgressBarColor(isPercentOfRightAnswersEnough: Boolean) {
        val colorResId = if (isPercentOfRightAnswersEnough) {
            android.R.color.holo_green_light
        } else {
            android.R.color.holo_red_light
        }
        val color = ContextCompat.getColor(requireContext(), colorResId)
        binding.progressBar.progressTintList = ColorStateList.valueOf(color)
    }

    private fun setSecondaryProgressBarValue(minPercent: Int) {
        binding.progressBar.secondaryProgress = minPercent
    }

    private fun setProgressAnswers(progressAnswer: String) {
        binding.tvAnswersProgress.text = progressAnswer
    }

    private fun setUpListeners() {

        for (tvOption in tvOptions) {
            tvOption.setOnClickListener {
                viewModel.chooseAnswer(tvOption.text.toString().toInt())
            }
        }
    }

    private fun setUpObservers() {



    }

    companion object {

        const val KEY_LEVEL = "level"
        const val NAME = "GameFragment"

        fun newInstance(level: Level): GameFragment {
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(KEY_LEVEL, level)
                }
            }
        }
    }
}