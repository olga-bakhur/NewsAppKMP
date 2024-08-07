package data.util

object FieldsRequestBuilder {

    private const val TRAIL_TEXT = "trailText"
    private const val BODY_TEXT = "bodyText"
    private const val BY_LINE = "byline"
    private const val LAST_MODIFIED = "lastModified"
    private const val PUBLICATION = "publication"
    private const val THUMBNAIL = "thumbnail"

    fun constructFeedRequestFields(): String =
        "$TRAIL_TEXT,$LAST_MODIFIED,$PUBLICATION,$THUMBNAIL"

    fun constructArticleDetailRequestFields(): String =
        "$TRAIL_TEXT,$BODY_TEXT,$BY_LINE,$LAST_MODIFIED,$PUBLICATION,$THUMBNAIL"
}