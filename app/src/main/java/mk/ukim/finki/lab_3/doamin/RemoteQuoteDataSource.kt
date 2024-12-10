package mk.ukim.finki.lab_3.doamin

import mk.ukim.finki.lab_3.retrofit.Quote



    interface RemoteQuoteDataSource {
        suspend fun search(): List<Quote>
    }

