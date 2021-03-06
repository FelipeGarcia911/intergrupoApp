package felipe.garcia.testapp.ui.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import felipe.garcia.testapp.R
import felipe.garcia.testapp.base.BaseActivity
import felipe.garcia.testapp.models.ProspectModel
import felipe.garcia.testapp.ui.OPEN_DETAILS
import felipe.garcia.testapp.ui.ProspectDetailEvent
import felipe.garcia.testapp.ui.fragments.EditProspectFragment
import felipe.garcia.testapp.ui.fragments.LogFragment
import felipe.garcia.testapp.ui.fragments.ProspectListFragment
import felipe.garcia.testapp.ui.viewModels.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import org.greenrobot.eventbus.Subscribe

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var currentFragment: Fragment
    lateinit var fragmentManager: FragmentManager
    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        init()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> navToProspectList()
            R.id.nav_gallery -> navToLogList()
            R.id.nav_share -> {
                viewModel.logoutAction()
                logoutAction()
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    @Subscribe
    fun onMessageEvent(event: ProspectDetailEvent) {
        when (event.eventType) {
            OPEN_DETAILS -> navToEditProspect(event.prospectModel)
            else -> showMessage("Error mostrando detalles")
        }
    }

    private fun navToProspectList() {
        currentFragment = ProspectListFragment()
        val fragmentName = ProspectListFragment.FRAGMENT_ID
        executeFragmentTransaction(currentFragment, fragmentName)
        setToolbarTitle(fragmentName)
    }

    private fun navToEditProspect(prospectModel: ProspectModel) {
        currentFragment = EditProspectFragment.newInstance(prospectModel)
        val fragmentName = EditProspectFragment.FRAGMENT_ID
        executeFragmentTransaction(currentFragment, fragmentName)
        setToolbarTitle(fragmentName)
    }

    private fun navToLogList() {
        currentFragment = LogFragment()
        val fragmentName = LogFragment.FRAGMENT_ID
        executeFragmentTransaction(currentFragment, fragmentName)
        setToolbarTitle(fragmentName)
    }

    private fun logoutAction() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun executeFragmentTransaction(fragment: Fragment, fragmentName: String) {
        fragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .replace(R.id.fragment_container, fragment, fragmentName)
                .addToBackStack(fragmentName)
                .commit()
        fragmentManager.executePendingTransactions()
    }

    private fun init() {
        super.registerEventBus()
        viewModel = MainActivityViewModel()
        fragmentManager = supportFragmentManager
        navToProspectList()
    }

    override fun onDestroy() {
        super.unregisterEventBus()
        super.onDestroy()
    }
}
