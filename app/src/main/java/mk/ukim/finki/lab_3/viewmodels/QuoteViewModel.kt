package mk.ukim.finki.lab_3.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mk.ukim.finki.lab_3.repository.QuoteRepository
import mk.ukim.finki.lab_3.retrofit.Quote
import mk.ukim.finki.lab_3.retrofit.QuoteResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuoteViewModel(private val quoteRepository: QuoteRepository) : ViewModel() {

    private val _quotes = MutableLiveData<List<Quote>>()
    fun getQuoteLiveData(): LiveData<List<Quote>> = _quotes

    private val _selectedQuote = MutableLiveData<Quote>()
    fun getSelectedQuote(): LiveData<Quote> = _selectedQuote

    fun selectQuote(quote: Quote) {
        _selectedQuote.value = quote
    }

    fun getQuotes() {
        viewModelScope.launch(Dispatchers.IO) {
            val quotes = quoteRepository.queryQuotes()
            _quotes.postValue(quotes)
        }
    }
}