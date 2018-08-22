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

public class FragmentFactory {
    public static BaseFragment createFragment(int pos,int type) {
        if(type== Constants.HOMEPAGR_TEACHER_TYPE){
            HashMap<Integer, BaseFragment> mBaseFragments = new HashMap<Integer, BaseFragment>();
            BaseFragment baseFragment = mBaseFragments.get(pos);
            if (baseFragment == null) {
                switch (pos) {
                    case 0:
                        baseFragment = new MathFragment();
                        break;
                    case 1:
                        baseFragment = new MathFragment();
                        break;
                    case 2:
                        baseFragment = new MathFragment();
                        break;
                }
                mBaseFragments.put(pos, baseFragment);
            }
            return baseFragment;
        }else if(type==Constants.MINE_HELP_TYPE){
            HashMap<Integer, BaseFragment> mBaseFragments = new HashMap<Integer, BaseFragment>();
            BaseFragment baseFragment = mBaseFragments.get(pos);
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
                mBaseFragments.put(pos, baseFragment);
            }
            return baseFragment;
        }
     return null;
    }
}
