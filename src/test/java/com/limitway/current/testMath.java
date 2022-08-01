package com.limitway.current;


import com.alibaba.fastjson.JSON;
import org.springframework.util.ResourceUtils;

import java.util.*;

public class testMath {
    public static void main(String[] args) {
        String str = "{\"nameAbbreviated\": \"国网信通产业集团\",\"nameShort\": null,\"id\": \"10801000\",\"parent\": \"#\",\"text\": \"国网信息通信产业集团有限公司\",\"type\": \"0\",\"code\": \"6800\",\"state\": {\"opened\": false,\"checked\": false},\"children\":null,\"hierarchyType\": null,\"hasChildren\": true}";
//        String jsonStr = JSON.toJSONString(str);
        ConfigTreeNode node = JSON.parseObject(str,ConfigTreeNode.class);
        System.out.println(node.toString());

    }


    public  static Integer doway(int num){
        int total = 0;
        int index = -1;
        for(int i = 0; num!=0 ;i++){
            if((num & 1) == 1){
                if(index != -1){
                    total = Math.max(total,i-index);
                }
                index = i;
            }
            num >>=1;
        }
        return total;

    }



    public static Integer doway1(int num){
        String numStr = Integer.toBinaryString(num);
        int index = 0; int total = 0;
        for (int i = 0; i < numStr.length(); i++) {
            if( numStr.charAt(i) == '1'){
                total = Math.max(total,i-index);
                index = i;
            }
        }
        return total;
    }


    public static boolean carPooling(int[][] trips, int capacity) {
        boolean result = true;
        int total = 0;
        int MinIndex = 0;
        Map<Integer,Integer> map = new HashMap();
        for(int i = 0;i< trips.length;i++){
            if(i == 0){
                MinIndex = trips[i][2];
            }else{
                if(trips[i][1] >= MinIndex){
                    for(int j = MinIndex;j<=trips[i][1]; j++){
                        if(map.containsKey(j)){
                            total = total - map.get(j);
                        }
                    }
                }
            }
            if(total + trips[i][0] <= capacity){
                total += trips[i][0];
                if(map.containsKey(trips[i][2])){
                    map.put(trips[i][2],trips[i][0]+map.get(trips[i][2]));
                }else{
                    map.put(trips[i][2],trips[i][0]);
                }

                MinIndex = Math.min(MinIndex,trips[i][2]);

            }else{
                result = false;
                break;
            }
        }
        return result;
    }



    public static int[] corpFlightBookings(int[][] bookings, int n) {
        int[] nums = new int[n];
        for (int[] booking : bookings) {
            nums[booking[0] - 1] += booking[2];
            if (booking[1] < n) {
                nums[booking[1]] -= booking[2];
            }
        }
        System.out.println(Arrays.toString(nums));
        for (int i = 1; i < n; i++) {
            nums[i] += nums[i - 1];
        }
        return nums;
    }


    public static int[] fullBloomFlowers(int[][] flowers, int[] persons) {
        TreeMap<Integer, Integer> tps = new TreeMap<>();
        for(int[] f : flowers){
            int l = f[0];
            int r = f[1];
            tps.put(l, tps.getOrDefault(l,0)+1);
            tps.put(r+1, tps.getOrDefault(r+1, 0)-1);
        }
        int presum = 0;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(Map.Entry<Integer, Integer> e : tps.entrySet()){
            map.put(e.getKey(), presum += e.getValue());
        }

        int plen = persons.length;
        int[] ans = new int[plen];
        for(int i = 0 ; i < plen ; i++){
            Map.Entry<Integer, Integer> e = map.floorEntry(persons[i]);
            if(e != null) ans[i] = e.getValue();
        }
        return ans;
    }




}
