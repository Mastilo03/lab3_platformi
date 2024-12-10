package mk.ukim.finki.lab_3.retrofit

import mk.ukim.finki.lab_3.doamin.RemoteQuoteDataSource


class RetrofitQuoteDataSource(private val quoteApiService: QuoteApiService): RemoteQuoteDataSource {
    override suspend fun search(): List<Quote> {
        val quoteResponse = quoteApiService.getQuotes() // go prakjame ovoj parametar na api-to
        val responseBody = quoteResponse.body() // go zemame body-to na response-ot
        if (quoteResponse.isSuccessful && responseBody != null) { //ako ne e uspesen ili ako e null
            return responseBody.quotes //vrati go results od odgovorot
        }
        throw Exception("Error searching quotes")
    }//vo sprotivno frlame exception


}

