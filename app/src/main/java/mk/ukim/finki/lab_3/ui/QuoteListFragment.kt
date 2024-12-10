package mk.ukim.finki.lab_3.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import mk.ukim.finki.lab_3.R
import mk.ukim.finki.lab_3.adapters.QuoteAdapter
import mk.ukim.finki.lab_3.databinding.FragmentQuoteListBinding
import mk.ukim.finki.lab_3.viewmodels.QuoteViewModel
import mk.ukim.finki.lab_3.viewmodels.QuotesViewModelFactory

class QuoteListFragment : Fragment(R.layout.fragment_quote_list) {
    private var _binding: FragmentQuoteListBinding? = null
    private val binding get() = _binding!!

    private lateinit var quotesViewModel: QuoteViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentQuoteListBinding.bind(view)

        val viewModelFactory = QuotesViewModelFactory(requireContext())
        quotesViewModel = ViewModelProvider(requireActivity(), viewModelFactory)[QuoteViewModel::class.java]

        val adapter = QuoteAdapter{ selectedQuote ->
            quotesViewModel.selectQuote(selectedQuote)
            parentFragmentManager.commit {
                replace(R.id.fragment_container_view,QuoteDetailsFragment())
                setReorderingAllowed(true)
                addToBackStack(null)
            }
        }

        binding.quoteRecyclerView.adapter = adapter

        quotesViewModel.getQuoteLiveData().observe(viewLifecycleOwner) {

            adapter.updateQuotes(it)
        }

        quotesViewModel.getQuotes()
    }
}
