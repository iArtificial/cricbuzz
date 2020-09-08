package com.sportz.interactive.ui.match.commentary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sportz.interactive.R
import com.sportz.interactive.ui.base.ScopedFragment
import com.sportz.interactive.ui.match.MatchViewModel
import com.sportz.interactive.ui.match.MatchViewModelFactory
import kotlinx.android.synthetic.main.commentary_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class CommentaryFragment : ScopedFragment(), KodeinAware {
    override val kodein by closestKodein()
    private val viewModelFactory: MatchViewModelFactory by instance()

    private lateinit var viewmodel: MatchViewModel

    companion object {
        fun newInstance() = CommentaryFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.commentary_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewmodel = ViewModelProviders.of(this, viewModelFactory).get(MatchViewModel::class.java)


        bindUI()
    }

    private fun bindUI() = launch {
        val matchLiveFeed = viewmodel.match.await()

        matchLiveFeed.observe(viewLifecycleOwner, Observer { response ->

            val notes = response.notes
            val commentary = arrayListOf<String>()
            commentary.addAll(notes.notesInningOne)
            commentary.addAll(notes.notesInningTwo)


            val commentsAdapter = CommentsAdapter(requireContext())

            recyclerView.apply {
                adapter = commentsAdapter
                layoutManager = LinearLayoutManager(requireContext())
                addItemDecoration(
                    DividerItemDecoration(
                        requireContext(),
                        LinearLayoutManager.VERTICAL
                    )
                )
            }
            commentsAdapter.setComments(commentary)
        }
        )

    }
}


