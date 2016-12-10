package com.parse.starter.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.View;

import com.parse.starter.R;
import com.parse.starter.fragments.HomeFragment;
import com.parse.starter.fragments.UsuariosFragment;

import java.util.HashMap;

public class TabsAdapter extends FragmentStatePagerAdapter {

    private Context context;
    private String[] abas = new String[]{"HOME", "USUÁRIOS"};
    private int[] icones  = new int[]{R.drawable.ic_home,R.drawable.ic_people};
    private int tamanhoIcone;
    private HashMap<Integer,Fragment> fragmentsUtilizados;

    public TabsAdapter(FragmentManager fm, Context c) {
        super(fm);
        this.context = c;
        double escala = this.context.getResources().getDisplayMetrics().density;
        tamanhoIcone = (int)(36 * escala);
        this.fragmentsUtilizados = new HashMap<>();
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;
        switch (position){

            case 0 :
                fragment = new HomeFragment();
                fragmentsUtilizados.put(position,fragment);
                break;
            case 1:
                fragment = new UsuariosFragment();
                fragmentsUtilizados.put(position,fragment);
                break;

        }
        return  fragment;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        super.destroyItem(container, position, object);
        fragmentsUtilizados.remove(position);
    }

    public Fragment getFragment(Integer indice){
        return fragmentsUtilizados.get(indice);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Drawable drawable = ContextCompat.getDrawable(this.context, icones[position]);
        drawable.setBounds(0,0,tamanhoIcone,tamanhoIcone);
        // ImagemSpan, serve para colocar imagem dentro de um texto
        ImageSpan imageSpan =  new ImageSpan(drawable);
        //Classe para retornar char sequence
        SpannableString spannableString = new SpannableString(" ");
        spannableString.setSpan(imageSpan,0,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableString;
    }

    @Override
    public int getCount() {
        return icones.length;
    }
}
