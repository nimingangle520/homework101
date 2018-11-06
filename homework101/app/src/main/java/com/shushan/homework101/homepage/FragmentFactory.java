package com.shushan.homework101.homepage;

import com.shushan.homework101.Constants;
import com.shushan.homework101.base.BaseFragment;
import com.shushan.homework101.mine.tutorship.CanceledFragment;
import com.shushan.homework101.mine.tutorship.CompleteFragment;
import com.shushan.homework101.mine.tutorship.ObligationFragment;
import com.shushan.homework101.mine.tutorship.WholeHelpFragment;

import java.util.HashMap;

/**
 * Fragment工厂类。
 */

public  class FragmentFactory {

     static HashMap<Integer, BaseFragment> mBaseFragments = new HashMap<Integer, BaseFragment>();
     static HashMap<Integer, BaseFragment> mBaseFragmentsHelp = new HashMap<Integer, BaseFragment>();

    public   static BaseFragment createFragment(int pos,int type) {

        if(type== Constants.HOMEPAGR_TEACHER_TYPE){

            BaseFragment baseFragment = mBaseFragments.get(pos);

            if (baseFragment == null) {
                switch (pos) {
                    case 0:
                        baseFragment = new ZhFragment();
                        break;
                    case 1:
                        baseFragment = new MathFragment();
                        break;
                    case 2:
                        baseFragment = new EnFragment();
                        break;
                    case 3:
                        baseFragment = new PhysicsFragment();
                        break;
                    case 4:
                        baseFragment = new ChemieFragment();
                        break;
                    case 5:
                        baseFragment = new OrganismFragment();
                        break;
                    case 6:
                        baseFragment = new HistoryFragment();
                        break;
                    case 7:
                        baseFragment = new GeogFragment();
                        break;
                    case 8:
                        baseFragment = new GDJYFragment();
                        break;

                }
                mBaseFragments.put(pos, baseFragment);
            }
            return baseFragment;
        }else if(type==Constants.MINE_HELP_TYPE){
            BaseFragment baseFragment = mBaseFragmentsHelp.get(pos);
            if (baseFragment == null) {
                switch (pos) {
                    case 0:
                        baseFragment = new WholeHelpFragment();
                        break;
                    case 1:
                        baseFragment = new ObligationFragment();
                        break;
                    case 2:
                        baseFragment = new CompleteFragment();
                        break;
                    case 3:
                        baseFragment = new CanceledFragment();
                        break;
                }
                mBaseFragmentsHelp.put(pos, baseFragment);
            }
            return baseFragment;
        }
     return null;
    }

}
