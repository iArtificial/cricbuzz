package com.sportz.interactive.ui.match.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sportz.interactive.R
import com.sportz.interactive.data.db.entity.Player
import com.sportz.interactive.ui.match.squad.PlayersAdapter
import kotlinx.android.synthetic.main.team_fragment.*

class TeamFragment : Fragment() {

    companion object {
        private const val PAGE_NUM = "PAGE_NUM"
        const val KEY_PLAYERS = "KEY_PLAYERS"
        fun newInstance(page: Int, players: ArrayList<Player?>): TeamFragment {
            val fragment = TeamFragment()
            val args = Bundle()
            args.putInt(PAGE_NUM, page)
            args.putSerializable(KEY_PLAYERS, players)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var viewModel: TeamViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.team_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TeamViewModel::class.java)
        // TODO: Use the ViewModel
        val playersAdapter = PlayersAdapter(requireContext())

        val bundle = arguments
        val array = bundle!!.getSerializable(KEY_PLAYERS) as ArrayList<Player?>

        recyclerView.apply {
            adapter = playersAdapter
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))
        }
        playersAdapter.setPlayers(array)

    }

}