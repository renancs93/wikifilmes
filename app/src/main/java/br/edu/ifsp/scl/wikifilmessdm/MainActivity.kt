package br.edu.ifsp.scl.wikifilmessdm

import android.Manifest
import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import br.edu.ifsp.scl.wikifilmessdm.Fragments.HomeFragment
import br.edu.ifsp.scl.wikifilmessdm.Fragments.SearchFragment
import br.edu.ifsp.scl.wikifilmessdm.Models.Movie
import com.kotlinpermissions.KotlinPermissions
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {

    private val INTERNET_REQUEST_CODE = 101
    private val TAG = "PERMISSION"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(R.string.app_name)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val abreFechaToogle: ActionBarDrawerToggle =
            ActionBarDrawerToggle(
                this,
                menuLateralDrawerLayout,
                toolbar,
                R.string.menu_open,
                R.string.menu_closed
            )
        menuLateralDrawerLayout.addDrawerListener(abreFechaToogle)
        abreFechaToogle.syncState()

        menuNavigationView.setNavigationItemSelectedListener { onNavigationItemSelected(it) }

        //inicia o primeiro fragment
        //substituiFragment(HomeFragment())
        substituiFragment(SearchFragment())

        permissions()
    }

    private fun permissions(){

        KotlinPermissions.with(this)
            .permissions(Manifest.permission.INTERNET)
            .ask()
    }

    private fun onNavigationItemSelected(item: MenuItem): Boolean {
        var retorno: Boolean = false
        when(item.itemId){
            //ação do botão Home
            R.id.homeMenuItem ->{
                substituiFragment(HomeFragment())
                retorno = true
            }
            //ação do botão Top 10
            R.id.top10MenuItem ->{
                substituiFragment(SearchFragment())
                retorno = true
            }
            //ação do botão sair
            R.id.sairMenuItem -> {
                finish()
                retorno = true
            }
        }
        menuLateralDrawerLayout.closeDrawer(GravityCompat.START)

        return retorno

    }

    fun substituiFragment(fragment: Fragment){

        val fragmentTarget = fragment;

        // Transaction para substituição de fragment
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragmentTarget)
        fragmentTransaction.commit()
    }

    interface MainInterfaceCallback{
        fun sendMovieDatails(movie : Movie)
    }

}
