package com.yuxiao;

import java.util.Arrays;

/**
 * 1109. 航班预订统计
 * <p>
 * 这里有n个航班，它们分别从 1 到 n 进行编号。
 * <p>
 * 有一份航班预订表bookings ，表中第i条预订记录bookings[i] = [firsti, lasti, seatsi]意味着在从 firsti到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi个座位。
 * <p>
 * 请你返回一个长度为 n 的数组answer，其中 answer[i] 是航班 i 上预订的座位总数。
 * <p>
 * 输入：bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
 * 输出：[10,55,45,25,25]
 * 解释：
 * 航班编号        1   2   3   4   5
 * 预订记录 1 ：   10  10
 * 预订记录 2 ：       20  20
 * 预订记录 3 ：       25  25  25  25
 * 总座位数：      10  55  45  25  25
 * 因此，answer = [10,55,45,25,25]
 * <p>
 * 输入：bookings = [[1,2,10],[2,2,15]], n = 2
 * 输出：[10,25]
 * 解释：
 * 航班编号        1   2
 * 预订记录 1 ：   10  10
 * 预订记录 2 ：       15
 * 总座位数：      10  25
 * 因此，answer = [10,25]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/corporate-flight-bookings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author yangjunwei
 * @date 2021-08-31 07:46
 */
public class CorporateFlightBookings1109 {


    public static void main(String[] args) {
        int[][] bookings = {{1, 2, 10}, {2, 3, 20}, {2, 5, 25}};
        int flight = 5;
        System.out.println(Arrays.toString(corpFlightBookings(bookings, flight)));
    }


    /**
     * 使用差分发进行计算(性能拉满)
     *
     * @param bookings 订票规则
     * @param n        总航班数
     * @return
     */
    public static int[] corpFlightBookingsDiffArray(int[][] bookings, int n) {
        int[] flightSeats = new int[n];

        for (int[] booking : bookings) {
            flightSeats[booking[0] - 1] += booking[2];
            if (booking[1] < n) {
                flightSeats[booking[1]] -= booking[2];
            }
        }

        for (int i = 1; i < flightSeats.length; i++) {
            flightSeats[i] += flightSeats[i - 1];
        }

        return flightSeats;
    }


    /**
     * @param bookings 订票规则
     * @param n        总航班数
     * @return
     */
    public static int[] corpFlightBookings(int[][] bookings, int n) {
        int[] flightSeats = new int[n];
        int length = bookings.length;
        int[] booking;
        for (int i = 0; i < length; i++) {
            booking = bookings[i];
            int from = booking[0], to = booking[1], seats = booking[2];
            for (int j = from - 1; j < to; j++) {
                flightSeats[j] += seats;
            }
        }
        return flightSeats;
    }

}
