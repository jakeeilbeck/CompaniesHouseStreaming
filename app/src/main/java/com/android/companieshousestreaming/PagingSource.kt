package com.android.companieshousestreaming

import androidx.paging.PagingSource
import com.android.companieshousestreaming.models.JsonResponse
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class PagingSource(private val retrofitService: RetrofitService) : PagingSource<Int, JsonResponse.Data>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, JsonResponse.Data> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response:JsonResponse  = retrofitService.getStream()
            val repos = response.data
            LoadResult.Page(
                data = repos,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (repos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}