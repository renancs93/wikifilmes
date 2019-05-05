package br.edu.ifsp.scl.wikifilmessdm

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

object Utils{

    public fun replaceFragment(fragmenCurrent: FragmentManager?, fragmentNext: Fragment) {
        val fragmentTarget = fragmentNext;

        // Transaction para substituição de fragment
        val fragmentTransaction = fragmenCurrent?.beginTransaction()
        fragmentTransaction?.replace(R.id.fragmentContainer, fragmentTarget)
        fragmentTransaction?.commit()
    }

}