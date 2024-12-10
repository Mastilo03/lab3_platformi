package mk.ukim.finki.lab_3.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mk.ukim.finki.lab_3.repository.QuoteRepository
import mk.ukim.finki.lab_3.retrofit.QuoteDbApiProvider
import mk.ukim.finki.lab_3.retrofit.RetrofitQuoteDataSource
import mk.ukim.finki.lab_3.utils.NetworkConnectivity

class QuotesViewModelFactory(private val context: Context): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(QuoteRepository::class.java)
            .newInstance(QuoteRepository( //do tuka imavme samo 1 dependency, a sega ke go dopolnime so novi
                RetrofitQuoteDataSource(QuoteDbApiProvider.getQuoteDbApi()),
                NetworkConnectivity(context)
            )) //na ovoj nacin go kompletiravme constructor-ot na MovieRepository

    }
}
