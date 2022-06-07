package com.diolkaee.alexandria.ui.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.diolkaee.alexandria.databinding.FragmentDetailsBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

fun interface RatingListener {
    operator fun invoke(rating: Float)
}

private const val LOG_TAG = "DetailsFragment"

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val args: DetailsFragmentArgs by navArgs()
    private val viewModel: DetailsViewModel by viewModel(parameters = { parametersOf(args.isbn) })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        setupViews()
        setupEvents()
        return binding.root
    }

    private fun setupViews() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.book.collect {
                    binding.book = it
                }
            }
        }
    }

    private fun setupEvents() = with(binding) {
        setOnSubmitNotes {
            val notes = notesInput.text?.toString()
            viewModel.setNotes(notes)
        }
        setOnRate {
            Log.d(LOG_TAG, "Rating changed to $it")
            viewModel.setRating(it)
        }
    }
}
