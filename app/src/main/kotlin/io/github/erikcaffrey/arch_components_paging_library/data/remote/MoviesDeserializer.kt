package io.github.erikcaffrey.arch_components_paging_library.data.remote

import com.google.gson.*
import java.lang.reflect.Type

class MoviesDeserializer<T> : JsonDeserializer<T> {

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): T = Gson().run {
        val moviesJsonObject = json?.asJsonObject?.get("results")?.asJsonArray
        fromJson(moviesJsonObject, typeOfT)
    }
}
