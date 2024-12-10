package mk.ukim.finki.lab_3.repository

import mk.ukim.finki.lab_3.retrofit.Quote
import mk.ukim.finki.lab_3.doamin.RemoteQuoteDataSource
import mk.ukim.finki.lab_3.utils.NetworkConnectivity


class QuoteRepository(
    private val remoteQuoteDataSource: RemoteQuoteDataSource,
    private val networkConnectivity: NetworkConnectivity
) {

    suspend fun queryQuotes(): List<Quote> {
        if (networkConnectivity.isNetworkAvailable) {
            return remoteQuoteDataSource.search()
        }
        return emptyList()
    }



}
