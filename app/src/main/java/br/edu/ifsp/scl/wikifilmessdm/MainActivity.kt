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
        substituiFragment(HomeFragment())

        permissions()
    }

    private fun permissions(){

        KotlinPermissions.with(this)
            .permissions(Manifest.permission.INTERNET)
            .ask()
    }

//    //Verifica se tem as permissões
//    private fun setupPermissions() {
//        val permission = ContextCompat.checkSelfPermission(baseContext, Manifest.permission.INTERNET)
//
//        if (permission != PackageManager.PERMISSION_GRANTED) {
//            Log.i(TAG, "Permission to internet denied")
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.INTERNET)) {
//
//                val builder = AlertDialog.Builder(baseContext)
//
//                builder.setMessage("Permission to access the internet is required")
//                    .setTitle("Permission required")
//                builder.setPositiveButton("OK") {
//                        dialog, id -> Log.i(TAG, "Clicked")
//                    makeRequest()
//                }
//
//                val dialog = builder.create()
//                dialog.show()
//
//            } else {
//                makeRequest()
//            }
//        }
//    }﻿
//
//    //Solicita Permissão
//    private fun makeRequest() {
//        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.INTERNET), INTERNET_REQUEST_CODE)
//    }
//
//    //Valida se foi concedido a Permissão
//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//        when (requestCode) {
//            INTERNET_REQUEST_CODE -> {
//                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
//                    Log.i(TAG, "Permission has been denied by user")
//                } else {
//                    Log.i(TAG, "Permission has been granted by user")
//                }
//            }
//        }
//    }

    private fun onNavigationItemSelected(item: MenuItem): Boolean {
        var retorno: Boolean = false
        when(item.itemId){
            R.id.sairMenuItem -> {
                finish()
                retorno = true
            }
        }
        menuLateralDrawerLayout.closeDrawer(GravityCompat.START)

        return retorno

    }

    private fun substituiFragment(fragment: Fragment){

        val fragmentTarget = fragment;

        // Transaction para substituição de fragment
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragmentTarget)
        fragmentTransaction.commit()
    }
}
