package br.edu.ifsp.scl.wikifilmessdm


import android.provider.Settings.Global.getString
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.widget.ImageView
import com.squareup.picasso.Picasso

object Utils{

    public fun replaceFragment(fragmenCurrent: FragmentManager?, fragmentNext: Fragment) {
        val fragmentTarget = fragmentNext;

        // Transaction para substituição de fragment
        val fragmentTransaction = fragmenCurrent?.beginTransaction()
        fragmentTransaction?.replace(R.id.fragmentContainer, fragmentTarget)
        fragmentTransaction?.commit()
    }

    public fun ImageView.loadPicasso(urlPoster: String) {
        Picasso.get().load(urlPoster).into(this)
    }

}