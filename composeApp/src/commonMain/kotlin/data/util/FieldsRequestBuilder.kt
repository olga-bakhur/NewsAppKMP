package data.util

object FieldsRequestBuilder {

    private const val trailText = "trailText"
    private const val bodyText = "bodyText"
    private const val byline = "byline"
    private const val lastModified = "lastModified"
    private const val publication = "publication"
    private const val thumbnail = "thumbnail"

    fun constructArticleListRequestFields(): String =
        "$trailText,$lastModified,$publication,$thumbnail"

    fun constructArticleDetailRequestFields(): String =
        "$trailText,$bodyText,$byline,$lastModified,$publication,$thumbnail"
}