package mk.ukim.finki.lab_3.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import mk.ukim.finki.lab_3.R
import mk.ukim.finki.lab_3.adapters.QuoteAdapter
import mk.ukim.finki.lab_3.databinding.FragmentQuoteDetailsBinding
import mk.ukim.finki.lab_3.viewmodels.QuoteViewModel
import mk.ukim.finki.lab_3.viewmodels.QuotesViewModelFactory

class QuoteDetailsFragment : Fragment(R.layout.fragment_quote_details) {

    private var _binding: FragmentQuoteDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var quotesViewModel: QuoteViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentQuoteDetailsBinding.bind(view)

       val viewModelFactory = QuotesViewModelFactory(requireContext())
        quotesViewModel = ViewModelProvider(this, viewModelFactory)[QuoteViewModel::class.java]



        quotesViewModel.getSelectedQuote().observe(viewLifecycleOwner) { quote ->
            quote?.let {
                binding.detailQuoteIdTextView.text = "ID: ${it.id}"
                binding.detailQuoteContentTextView.text = "Quote: ${it.quote}"
                binding.detailQuoteAuthorTextView.text = "Author: ${it.author}"
            }
        }




    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

