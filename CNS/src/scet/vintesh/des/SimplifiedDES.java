/*
 * Implemented as Tutorial of Masters Program 
 * M.E. - Computer Engineering 
 * Network Security
 * SCET, Surat
 */
package scet.vintesh.des;

/**
 * This program is the Simplified/Modified version of: <b>URL is missing</b>
 *
 * @author Vintesh
 */
public class SimplifiedDES {

    public int K1, K2;    // subkeys
    // permutations
    public static final int P10[] = {3, 5, 2, 7, 4, 10, 1, 9, 8, 6};
    public static final int P10max = 10;
    public static final int P8[] = {6, 3, 7, 4, 8, 5, 10, 9};
    public static final int P8max = 10;
    public static final int P4[] = {2, 4, 3, 1};
    public static final int P4max = 4;
    public static final int IP[] = {2, 6, 3, 1, 4, 8, 5, 7};
    public static final int IPmax = 8;
    public static final int IPI[] = {4, 1, 3, 5, 7, 2, 8, 6};
    public static final int IPImax = 8;
    public static final int EP[] = {4, 1, 2, 3, 2, 3, 4, 1};
    public static final int EPmax = 4;
    public static final int S0[][] = {
        {1, 0, 3, 2},
        {3, 2, 1, 0},
        {0, 2, 1, 3},
        {3, 1, 3, 2}
    };
    public static final int S1[][] = {
        {0, 1, 2, 3},
        {2, 0, 1, 3},
        {3, 0, 1, 0},
        {2, 1, 0, 3}
    };

    // permute bits
    //
    public static int permute(int x, int p[], int pmax) {
        int y = 0;

        for (int i = 0; i < p.length; ++i) {
            y <<= 1;
            y |= (x >> (pmax - p[i])) & 1;
        }

        return y;
    }

    // F function
    //
    public static int F(int R, int K) {
        int t = permute(R, EP, EPmax) ^ K;
        int t0 = (t >> 4) & 0xF;
        int t1 = t & 0xF;

        t0 = S0[ ((t0 & 0x8) >> 2) | (t0 & 1)][ (t0 >> 1) & 0x3];
        t1 = S1[ ((t1 & 0x8) >> 2) | (t1 & 1)][ (t1 >> 1) & 0x3];

        t = permute((t0 << 2) | t1, P4, P4max);

        return t;
    }

    // fK function
    //
    public static int fK(int m, int K) {
        int L = (m >> 4) & 0xF;
        int R = m & 0xF;

        return ((L ^ F(R, K)) << 4) | R;
    }

    // switch function
    //
    public static int SW(int x) {
        return ((x & 0xF) << 4) | ((x >> 4) & 0xF);
    }

    // encrypt one byte
    //
    public byte encrypt(int m) {
        m = permute(m, IP, IPmax);
        m = fK(m, K1);
        m = SW(m);
        m = fK(m, K2);
        m = permute(m, IPI, IPImax);

        return (byte) m;
    }

    // decrypt one byte
    //
    public byte decrypt(int m) {
        m = permute(m, IP, IPmax);
        m = fK(m, K2);
        m = SW(m);
        m = fK(m, K1);
        m = permute(m, IPI, IPImax);

        return (byte) m;
    }

    // print n bits in binary
    //
    public static void printb(int x, int n) {
        int mask = 1 << (n - 1);

        while (mask > 0) {
            System.out.print(((x & mask) == 0) ? '0' : '1');
            mask >>= 1;
        }
    }

    // constructor - initializes K1 and K2
    //
    public SimplifiedDES(int K) {
        K = permute(K, P10, P10max);

        // 5-bit parts of K
        //
        int t1 = (K >> 5) & 0x1F;
        int t2 = K & 0x1F;

        // LS-1
        //
        t1 = ((t1 & 0xF) << 1) | ((t1 & 0x10) >> 4);
        t2 = ((t2 & 0xF) << 1) | ((t2 & 0x10) >> 4);

        K1 = permute((t1 << 5) | t2, P8, P8max);

        // LS-2
        //
        t1 = ((t1 & 0x7) << 2) | ((t1 & 0x18) >> 3);
        t2 = ((t2 & 0x7) << 2) | ((t2 & 0x18) >> 3);

        K2 = permute((t1 << 5) | t2, P8, P8max);
    }
}
