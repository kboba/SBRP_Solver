package pl.kboba.sbrp.model.initializers;

import pl.kboba.sbrp.model.BusStop;

import java.util.ArrayList;
import java.util.List;

public final class BusStopsInitializer {
    private BusStopsInitializer(){

    }

    public static List<BusStop> testingInitialize() {
        List<BusStop> busStops = new ArrayList<>();
        busStops.add(new BusStop(100, 40, 10, true));
        busStops.add(new BusStop(2, 10, 15));
        busStops.add(new BusStop(3, 25, 10));
        busStops.add(new BusStop(4, 25, 20));
        busStops.add(new BusStop(5, 40, 20));
        return busStops;
    }

    public static List<BusStop> basicInitialize() {
        List<BusStop> busStops = new ArrayList<>();
        busStops.add(new BusStop(0, 10, 10));
        busStops.add(new BusStop(1, 10, 20));
        busStops.add(new BusStop(2, 20, 10));
        busStops.add(new BusStop(3, 20, 20));
        busStops.add(new BusStop(4, 30, 5));
        busStops.add(new BusStop(5, 30, 15));
        busStops.add(new BusStop(6, 30, 25));
        busStops.add(new BusStop(7, 25, 20));
        busStops.add(new BusStop(100, 40, 10, true));
        return busStops;
    }

    public static List<BusStop> basicMoreMessedInitialize() {
        List<BusStop> busStops = new ArrayList<>();
        busStops.add(new BusStop(1, 10, 20));
        busStops.add(new BusStop(5, 30, 15));
        busStops.add(new BusStop(0, 10, 10));
        busStops.add(new BusStop(6, 30, 25));
        busStops.add(new BusStop(3, 20, 20));
        busStops.add(new BusStop(4, 30, 5));
        busStops.add(new BusStop(2, 20, 10));
        busStops.add(new BusStop(7, 25, 20));
        busStops.add(new BusStop(100, 40, 10, true));
        return busStops;
    }

    public static List<BusStop> squareInitialize() {
        List<BusStop> busStops = new ArrayList<>();
        busStops.add(new BusStop(0, 10, 10));
        busStops.add(new BusStop(1, 10, 20));
        busStops.add(new BusStop(2, 20, 10));
        busStops.add(new BusStop(3, 20, 20));
        busStops.add(new BusStop(4, 30, 10));
        busStops.add(new BusStop(5, 30, 20));
        busStops.add(new BusStop(6, 30, 30));
        busStops.add(new BusStop(7, 30, 40));
        busStops.add(new BusStop(8, 10, 30));
        busStops.add(new BusStop(9, 20, 30));
        busStops.add(new BusStop(10, 10, 40));
        busStops.add(new BusStop(11, 20, 40));
        busStops.add(new BusStop(12, 40, 10));
        busStops.add(new BusStop(13, 40, 20));
        busStops.add(new BusStop(14, 40, 30));
        busStops.add(new BusStop(15, 40, 40));
        busStops.add(new BusStop(100, 50, 25, true));
        return busStops;
    }

    public static List<BusStop> complicatedInitialize() {
        List<BusStop> busStops = new ArrayList<>();
        busStops.add(new BusStop(0, 5, 9));
        busStops.add(new BusStop(1, 14, 24));
        busStops.add(new BusStop(2, 19, 34));
        busStops.add(new BusStop(3, 12, 12));
        busStops.add(new BusStop(4, 15, 32));
        busStops.add(new BusStop(5, 32, 12));
        busStops.add(new BusStop(6, 45, 21));
        busStops.add(new BusStop(7, 13, 49));
        busStops.add(new BusStop(8, 27, 38));
        busStops.add(new BusStop(9, 20, 17));
        busStops.add(new BusStop(10, 7, 29));
        busStops.add(new BusStop(11, 37, 16));
        busStops.add(new BusStop(12, 3, 41));
        busStops.add(new BusStop(13, 26, 31));
        busStops.add(new BusStop(14, 19, 23));
        busStops.add(new BusStop(15, 29, 7));
        busStops.add(new BusStop(16, 15, 10));
        busStops.add(new BusStop(17, 37, 6));
        busStops.add(new BusStop(18, 24, 10));
        busStops.add(new BusStop(19, 13, 5));
        busStops.add(new BusStop(100, 50, 25, true));
        return busStops;
    }

    public static List<BusStop> houndredStopsInitialize() {
        List<BusStop> busStops = new ArrayList<>();
        busStops.add(new BusStop(0, 585, 664));
        busStops.add(new BusStop(1, 691, 141));
        busStops.add(new BusStop(2, 423, 347));
        busStops.add(new BusStop(3, 151, 381));
        busStops.add(new BusStop(4, 303, 813));
        busStops.add(new BusStop(5, 222, 296));
        busStops.add(new BusStop(6, 711, 846));
        busStops.add(new BusStop(7, 785, 924));
        busStops.add(new BusStop(8, 291, 327));
        busStops.add(new BusStop(9, 870, 836));
        busStops.add(new BusStop(10, 119, 332));
        busStops.add(new BusStop(11, 821, 568));
        busStops.add(new BusStop(12, 875, 647));
        busStops.add(new BusStop(13, 29, 616));
        busStops.add(new BusStop(14, 591, 17));
        busStops.add(new BusStop(15, 429, 210));
        busStops.add(new BusStop(16, 519, 214));
        busStops.add(new BusStop(17, 274, 174));
        busStops.add(new BusStop(18, 555, 825));
        busStops.add(new BusStop(19, 563, 906));
        busStops.add(new BusStop(20, 376, 408));
        busStops.add(new BusStop(21, 586, 257));
        busStops.add(new BusStop(22, 132, 908));
        busStops.add(new BusStop(23, 850, 678));
        busStops.add(new BusStop(24, 290, 12));
        busStops.add(new BusStop(25, 31, 360));
        busStops.add(new BusStop(26, 566, 997));
        busStops.add(new BusStop(27, 980, 398));
        busStops.add(new BusStop(28, 887, 347));
        busStops.add(new BusStop(29, 587, 234));
        busStops.add(new BusStop(30, 711, 755));
        busStops.add(new BusStop(31, 661, 388));
        busStops.add(new BusStop(32, 87, 324));
        busStops.add(new BusStop(33, 367, 752));
        busStops.add(new BusStop(34, 279, 297));
        busStops.add(new BusStop(35, 929, 436));
        busStops.add(new BusStop(36, 256, 943));
        busStops.add(new BusStop(37, 47, 303));
        busStops.add(new BusStop(38, 499, 496));
        busStops.add(new BusStop(39, 236, 472));
        busStops.add(new BusStop(40, 960, 281));
        busStops.add(new BusStop(41, 247, 850));
        busStops.add(new BusStop(42, 745, 582));
        busStops.add(new BusStop(43, 506, 54));
        busStops.add(new BusStop(44, 694, 829));
        busStops.add(new BusStop(45, 887, 236));
        busStops.add(new BusStop(46, 251, 911));
        busStops.add(new BusStop(47, 43, 522));
        busStops.add(new BusStop(48, 136, 481));
        busStops.add(new BusStop(49, 987, 186));
        busStops.add(new BusStop(50, 66, 98));
        busStops.add(new BusStop(51, 783, 666));
        busStops.add(new BusStop(52, 32, 372));
        busStops.add(new BusStop(53, 792, 272));
        busStops.add(new BusStop(54, 515, 630));
        busStops.add(new BusStop(55, 873, 26));
        busStops.add(new BusStop(56, 767, 15));
        busStops.add(new BusStop(57, 943, 420));
        busStops.add(new BusStop(58, 879, 457));
        busStops.add(new BusStop(59, 626, 451));
        busStops.add(new BusStop(60, 474, 930));
        busStops.add(new BusStop(61, 26, 25));
        busStops.add(new BusStop(62, 816, 392));
        busStops.add(new BusStop(63, 822, 672));
        busStops.add(new BusStop(64, 789, 228));
        busStops.add(new BusStop(65, 587, 295));
        busStops.add(new BusStop(66, 604, 208));
        busStops.add(new BusStop(67, 965, 507));
        busStops.add(new BusStop(68, 32, 9));
        busStops.add(new BusStop(69, 549, 38));
        busStops.add(new BusStop(70, 653, 889));
        busStops.add(new BusStop(71, 359, 219));
        busStops.add(new BusStop(72, 885, 160));
        busStops.add(new BusStop(73, 444, 775));
        busStops.add(new BusStop(74, 260, 733));
        busStops.add(new BusStop(75, 714, 242));
        busStops.add(new BusStop(76, 941, 268));
        busStops.add(new BusStop(77, 452, 197));
        busStops.add(new BusStop(78, 845, 85));
        busStops.add(new BusStop(79, 482, 203));
        busStops.add(new BusStop(80, 56, 942));
        busStops.add(new BusStop(81, 924, 14));
        busStops.add(new BusStop(82, 21, 575));
        busStops.add(new BusStop(83, 115, 588));
        busStops.add(new BusStop(84, 249, 492));
        busStops.add(new BusStop(85, 932, 701));
        busStops.add(new BusStop(86, 569, 111));
        busStops.add(new BusStop(87, 748, 435));
        busStops.add(new BusStop(88, 924, 849));
        busStops.add(new BusStop(89, 916, 550));
        busStops.add(new BusStop(90, 365, 964));
        busStops.add(new BusStop(91, 999, 191));
        busStops.add(new BusStop(92, 856, 537));
        busStops.add(new BusStop(93, 381, 931));
        busStops.add(new BusStop(94, 923, 186));
        busStops.add(new BusStop(95, 228, 270));
        busStops.add(new BusStop(96, 988, 482));
        busStops.add(new BusStop(97, 744, 45));
        busStops.add(new BusStop(98, 106, 489));
        busStops.add(new BusStop(100, 713, 295, true));
        return busStops;
    }
}