package com.diolkaee.alexandria.ui.details

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.diolkaee.alexandria.R
import com.diolkaee.alexandria.databinding.DialogEditNotesBinding
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
    private val navController: NavController
        get() = findNavController()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
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
                    if (it != null) {
                        setMarkedMenuItem(it.marked)
                    }
                }
            }
        }
    }

    private fun setupEvents() = with(binding) {
        setOnRate { viewModel.setRating(it) }
        setOnNavigateUp { navController.navigateUp() }
        setOnEditNotes { openEditNotesDialog() }
        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.mark -> viewModel.toggleMark()
                R.id.delete -> openDeleteDialog()
                else -> throw IllegalArgumentException("No menu item with id: ${item.itemId}")
            }
            true
        }
    }

    private fun openEditNotesDialog() {
        val layoutInflater = LayoutInflater.from(context)
        val dialogBinding = DialogEditNotesBinding.inflate(layoutInflater, view as ViewGroup, false).apply {
            initialText = viewModel.book.value?.notes
        }
        AlertDialog.Builder(context)
            .setView(dialogBinding.root)
            .setPositiveButton(R.string.common_dialog_confirm) { _, _ ->
                // We want blank notes to be handled as non existent (= null)
                val input = dialogBinding.input.text?.ifBlank { null }?.toString()
                viewModel.setNotes(input)
            }
            .setNegativeButton(R.string.common_dialog_cancel) { _, _ -> Unit }
            .show()
    }

    private fun openDeleteDialog() {
        AlertDialog.Builder(context)
            .setTitle(R.string.details_dialog_delete_title)
            .setMessage(R.string.details_dialog_delete_message)
            .setPositiveButton(R.string.common_dialog_confirm) { _, _ ->
                viewModel.deleteBook()
                navController.navigateUp()
            }
            .setNegativeButton(R.string.common_dialog_cancel) { _, _ -> Unit }
            .show()
    }

    /** This cannot be done via checked state:
     * https://stackoverflow.com/questions/6683186/menuitems-checked-state-is-not-shown-correctly-by-its-icon
     */
    private fun setMarkedMenuItem(isMarked: Boolean) {
        val markMenuItem = binding.toolbar.menu.findItem(R.id.mark)
        val iconId = if (isMarked) R.drawable.ic_check else R.drawable.ic_awesome
        markMenuItem.icon = ResourcesCompat.getDrawable(resources, iconId, null)
    }
}
