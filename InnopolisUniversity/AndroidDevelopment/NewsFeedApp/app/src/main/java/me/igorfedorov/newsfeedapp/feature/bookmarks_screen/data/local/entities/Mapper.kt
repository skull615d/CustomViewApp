package me.igorfedorov.newsfeedapp.feature.bookmarks_screen.data.local.entities

import me.igorfedorov.newsfeedapp.feature.news_feed_screen.domain.model.Article
import me.igorfedorov.newsfeedapp.feature.news_feed_screen.domain.model.Source

fun Article.toBookmark() = BookmarkEntity(
    url = url,
    author = author,
    content = content,
    description = description,
    publishedAt = publishedAt,
    sourceEntity = source?.toSourceEntity(),
    title = title,
    urlToImage = urlToImage
)

fun Source.toSourceEntity() = SourceEntity(
    id = id,
    name = name
)

fun BookmarkEntity.toArticle() = Article(
    author = author,
    content = content,
    description = description,
    publishedAt = publishedAt,
    source = sourceEntity?.toSource(),
    title = title,
    url = url,
    urlToImage = urlToImage
)

fun SourceEntity.toSource() = Source(
    id = id,
    name = name
)