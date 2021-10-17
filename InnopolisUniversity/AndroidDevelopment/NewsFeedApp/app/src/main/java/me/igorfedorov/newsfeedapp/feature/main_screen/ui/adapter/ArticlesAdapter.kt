package me.igorfedorov.newsfeedapp.feature.main_screen.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import me.igorfedorov.newsfeedapp.databinding.ItemArticleBinding
import me.igorfedorov.newsfeedapp.feature.main_screen.domain.model.Article

class ArticleAdapter : AsyncListDifferDelegationAdapter<Article>(ArticleDiffUtilCallback()) {

    init {
        delegatesManager.addDelegate(articleAdapterDelegate())
    }

    private fun articleAdapterDelegate() =
        adapterDelegateViewBinding<Article, Article, ItemArticleBinding>(
            { layoutInflater, parent -> ItemArticleBinding.inflate(layoutInflater, parent, false) }
        ) {

            bind {
                binding.apply {
                    authorTextView.text = item.author
                    contentTextView.text = item.content
                }
            }
        }

    class ArticleDiffUtilCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
}