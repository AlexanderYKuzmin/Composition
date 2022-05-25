package com.example.composition.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.composition.R
import com.example.composition.databinding.FragmentGameFinishedBinding
import com.example.composition.domain.entity.GameResult

class GameFinishedFragment : Fragment() {
    //private var gameResult: GameResult? = null
    private val args by navArgs<GameFinishedFragmentArgs>()
    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
    get() = _binding ?: throw RuntimeException("GameFinishFragment == null")

    /*private val viewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        )[GameViewModel::class.java]
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                retryGame()
            }
        })*/
        binding.buttonRetry.setOnClickListener {
            retryGame()
        }
        setTextData()
        setPictureWinner()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding == null
    }

    /*private fun parseArgs() {
        gameResult = requireArguments().getParcelable(GAME_RESULT)
    }*/

    private fun retryGame() {
        /*requireActivity().supportFragmentManager.popBackStack(GameFragment.NAME, FragmentManager.POP_BACK_STACK_INCLUSIVE)*/
        findNavController().popBackStack()
    }

    private fun setTextData() {
        val reqAnswers = getString(R.string.required_scores)
        binding.tvRequiredAnswers.text = String.format(reqAnswers, args.gameResult.gameSettings?.minCountOfRightAnswers)

        val rightAnswers = getString(R.string.score_answers)
        binding.tvScoreAnswers.text = String.format(rightAnswers, args.gameResult.countOfRightAnswers)

        val reqPercentage = getString(R.string.required_percentage)
        binding.tvRequiredPercentage.text = String.format(reqPercentage, args.gameResult.gameSettings?.minPercentOfRightAnswers)

        val rightPercentage = getString(R.string.score_percentage)
        binding.tvScorePercentage.text = String.format(rightPercentage, args.gameResult.percentOfRightAnswers)
    }

    private fun setPictureWinner() {
        val winner = args.gameResult.winner
        val smile: Int = if (winner) {
            R.drawable.ic_smile
        } else {
            R.drawable.too_sad_26009
        }
        binding.emojiResult.setImageDrawable(ContextCompat.getDrawable(requireContext(), smile))
    }

    companion object {
        const val GAME_RESULT = "game_result"
    }
}