package com.mvpdemo.luch;

import static org.junit.Assert.assertEquals;

import com.mvpdemo.luch.utils.L;

import org.junit.Test;

import java.text.DecimalFormat;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void tsetUpdate() {
        if (testUpdate()) {
            System.out.print("-----------true");
        } else {
            System.out.print("-----------false");
        }
    }

    private boolean testUpdate() {
        String nversion = "3.1.5"; //更新
        String bversion = "3.1.0"; //本地
        if (!nversion.equals(bversion)) {
            String nv = nversion.replace("v", "");
            String bv = bversion.replace("v", "");
            try {
                String[] nvarray = nv.split("\\.");
                String[] bvarray = bv.split("\\.");
                for (int i = 0; i < nvarray.length; i++) {
                    int nvs = Integer.parseInt(nvarray[i]);
                    if (bvarray.length > i) {
                        int bvs = Integer.parseInt(bvarray[i]);
                        if (nvs > bvs) {
                            return true;
                        } else if (nvs < bvs) {
                            return false;
                        }
                    } else {
                        if (nvs > 0) {
                            return true;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                L.v("版本更新比较失败");
                return false;
            }
        } else {
            return false;
        }
        return false;
    }


    @Test
    public void formatNum() {
        double num = 1.8;
        String str = String.format("%.2f", num);
        System.out.print("输出---------" + str);
        if (str != null && !"null".equals(str) && !"".equals(str)) {
            //   str=str.replaceAll("\\,","");
            double amt = Double.parseDouble(str);
            DecimalFormat decimalFormat = new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
            String decimalBalanceString = decimalFormat.format(amt);
            System.out.println("输出---------" + decimalBalanceString);
        } else {
            System.out.println("0.00");
        }
    }

}