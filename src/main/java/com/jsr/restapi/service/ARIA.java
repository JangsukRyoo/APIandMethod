package com.jsr.restapi.service;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Arrays;


public class ARIA {

    private int[][] S = {
            // S-box type 1
            {  0x63,  0x7c,  0x77,  0x7b,  0xf2,
                    0x6b,  0x6f,  0xc5,  0x30,
                    0x01,  0x67,  0x2b,  0xfe,
                    0xd7,  0xab,  0x76,  0xca,
                    0x82,  0xc9,  0x7d,  0xfa,
                    0x59,  0x47,  0xf0,  0xad,
                    0xd4,  0xa2,  0xaf,  0x9c,
                    0xa4,  0x72,  0xc0,  0xb7,
                    0xfd,  0x93,  0x26,  0x36,
                    0x3f,  0xf7,  0xcc,  0x34,
                    0xa5,  0xe5,  0xf1,  0x71,
                    0xd8,  0x31,  0x15,  0x04,
                    0xc7,  0x23,  0xc3,  0x18,
                    0x96,  0x05,  0x9a,  0x07,
                    0x12,  0x80,  0xe2,  0xeb,
                    0x27,  0xb2,  0x75,  0x09,
                    0x83,  0x2c,  0x1a,  0x1b,
                    0x6e,  0x5a,  0xa0,  0x52,
                    0x3b,  0xd6,  0xb3,  0x29,
                    0xe3,  0x2f,  0x84,  0x53,
                    0xd1,  0x00,  0xed,  0x20,
                    0xfc,  0xb1,  0x5b,  0x6a,
                    0xcb,  0xbe,  0x39,  0x4a,
                    0x4c,  0x58,  0xcf,  0xd0,
                    0xef,  0xaa,  0xfb,  0x43,
                    0x4d,  0x33,  0x85,  0x45,
                    0xf9,  0x02,  0x7f,  0x50,
                    0x3c,  0x9f,  0xa8,  0x51,
                    0xa3,  0x40,  0x8f,  0x92,
                    0x9d,  0x38,  0xf5,  0xbc,
                    0xb6,  0xda,  0x21,  0x10,
                    0xff,  0xf3,  0xd2,  0xcd,
                    0x0c,  0x13,  0xec,  0x5f,
                    0x97,  0x44,  0x17,  0xc4,
                    0xa7,  0x7e,  0x3d,  0x64,
                    0x5d,  0x19,  0x73,  0x60,
                    0x81,  0x4f,  0xdc,  0x22,
                    0x2a,  0x90,  0x88,  0x46,
                    0xee,  0xb8,  0x14,  0xde,
                    0x5e,  0x0b,  0xdb,  0xe0,
                    0x32,  0x3a,  0x0a,  0x49,
                    0x06,  0x24,  0x5c,  0xc2,
                    0xd3,  0xac,  0x62,  0x91,
                    0x95,  0xe4,  0x79,  0xe7,
                    0xc8,  0x37,  0x6d,  0x8d,
                    0xd5,  0x4e,  0xa9,  0x6c,
                    0x56,  0xf4,  0xea,  0x65,
                    0x7a,  0xae,  0x08,  0xba,
                    0x78,  0x25,  0x2e,  0x1c,
                    0xa6,  0xb4,  0xc6,  0xe8,
                    0xdd,  0x74,  0x1f,  0x4b,
                    0xbd,  0x8b,  0x8a,  0x70,
                    0x3e,  0xb5,  0x66,  0x48,
                    0x03,  0xf6,  0x0e,  0x61,
                    0x35,  0x57,  0xb9,  0x86,
                    0xc1,  0x1d,  0x9e,  0xe1,
                    0xf8,  0x98,  0x11,  0x69,
                    0xd9,  0x8e,  0x94,  0x9b,
                    0x1e,  0x87,  0xe9,  0xce,
                    0x55,  0x28,  0xdf,  0x8c,
                    0xa1,  0x89,  0x0d,  0xbf,
                    0xe6,  0x42,  0x68,  0x41,
                    0x99,  0x2d,  0x0f,  0xb0,
                    0x54,  0xbb,  0x16 },
            // S-box type 2
            {  0xe2,  0x4e,  0x54,  0xfc,  0x94,
                    0xc2,  0x4a,  0xcc,  0x62,
                    0x0d,  0x6a,  0x46,  0x3c,
                    0x4d,  0x8b,  0xd1,  0x5e,
                    0xfa,  0x64,  0xcb,  0xb4,
                    0x97,  0xbe,  0x2b,  0xbc,
                    0x77,  0x2e,  0x03,  0xd3,
                    0x19,  0x59,  0xc1,  0x1d,
                    0x06,  0x41,  0x6b,  0x55,
                    0xf0,  0x99,  0x69,  0xea,
                    0x9c,  0x18,  0xae,  0x63,
                    0xdf,  0xe7,  0xbb,  0x00,
                    0x73,  0x66,  0xfb,  0x96,
                    0x4c,  0x85,  0xe4,  0x3a,
                    0x09,  0x45,  0xaa,  0x0f,
                    0xee,  0x10,  0xeb,  0x2d,
                    0x7f,  0xf4,  0x29,  0xac,
                    0xcf,  0xad,  0x91,  0x8d,
                    0x78,  0xc8,  0x95,  0xf9,
                    0x2f,  0xce,  0xcd,  0x08,
                    0x7a,  0x88,  0x38,  0x5c,
                    0x83,  0x2a,  0x28,  0x47,
                    0xdb,  0xb8,  0xc7,  0x93,
                    0xa4,  0x12,  0x53,  0xff,
                    0x87,  0x0e,  0x31,  0x36,
                    0x21,  0x58,  0x48,  0x01,
                    0x8e,  0x37,  0x74,  0x32,
                    0xca,  0xe9,  0xb1,  0xb7,
                    0xab,  0x0c,  0xd7,  0xc4,
                    0x56,  0x42,  0x26,  0x07,
                    0x98,  0x60,  0xd9,  0xb6,
                    0xb9,  0x11,  0x40,  0xec,
                    0x20,  0x8c,  0xbd,  0xa0,
                    0xc9,  0x84,  0x04,  0x49,
                    0x23,  0xf1,  0x4f,  0x50,
                    0x1f,  0x13,  0xdc,  0xd8,
                    0xc0,  0x9e,  0x57,  0xe3,
                    0xc3,  0x7b,  0x65,  0x3b,
                    0x02,  0x8f,  0x3e,  0xe8,
                    0x25,  0x92,  0xe5,  0x15,
                    0xdd,  0xfd,  0x17,  0xa9,
                    0xbf,  0xd4,  0x9a,  0x7e,
                    0xc5,  0x39,  0x67,  0xfe,
                    0x76,  0x9d,  0x43,  0xa7,
                    0xe1,  0xd0,  0xf5,  0x68,
                    0xf2,  0x1b,  0x34,  0x70,
                    0x05,  0xa3,  0x8a,  0xd5,
                    0x79,  0x86,  0xa8,  0x30,
                    0xc6,  0x51,  0x4b,  0x1e,
                    0xa6,  0x27,  0xf6,  0x35,
                    0xd2,  0x6e,  0x24,  0x16,
                    0x82,  0x5f,  0xda,  0xe6,
                    0x75,  0xa2,  0xef,  0x2c,
                    0xb2,  0x1c,  0x9f,  0x5d,
                    0x6f,  0x80,  0x0a,  0x72,
                    0x44,  0x9b,  0x6c,  0x90,
                    0x0b,  0x5b,  0x33,  0x7d,
                    0x5a,  0x52,  0xf3,  0x61,
                    0xa1,  0xf7,  0xb0,  0xd6,
                    0x3f,  0x7c,  0x6d,  0xed,
                    0x14,  0xe0,  0xa5,  0x3d,
                    0x22,  0xb3,  0xf8,  0x89,
                    0xde,  0x71,  0x1a,  0xaf,
                    0xba,  0xb5,  0x81 },
            // inverse of S-box type 1
            {  0x52,  0x09,  0x6a,  0xd5,  0x30,
                    0x36,  0xa5,  0x38,  0xbf,
                    0x40,  0xa3,  0x9e,  0x81,
                    0xf3,  0xd7,  0xfb,  0x7c,
                    0xe3,  0x39,  0x82,  0x9b,
                    0x2f,  0xff,  0x87,  0x34,
                    0x8e,  0x43,  0x44,  0xc4,
                    0xde,  0xe9,  0xcb,  0x54,
                    0x7b,  0x94,  0x32,  0xa6,
                    0xc2,  0x23,  0x3d,  0xee,
                    0x4c,  0x95,  0x0b,  0x42,
                    0xfa,  0xc3,  0x4e,  0x08,
                    0x2e,  0xa1,  0x66,  0x28,
                    0xd9,  0x24,  0xb2,  0x76,
                    0x5b,  0xa2,  0x49,  0x6d,
                    0x8b,  0xd1,  0x25,  0x72,
                    0xf8,  0xf6,  0x64,  0x86,
                    0x68,  0x98,  0x16,  0xd4,
                    0xa4,  0x5c,  0xcc,  0x5d,
                    0x65,  0xb6,  0x92,  0x6c,
                    0x70,  0x48,  0x50,  0xfd,
                    0xed,  0xb9,  0xda,  0x5e,
                    0x15,  0x46,  0x57,  0xa7,
                    0x8d,  0x9d,  0x84,  0x90,
                    0xd8,  0xab,  0x00,  0x8c,
                    0xbc,  0xd3,  0x0a,  0xf7,
                    0xe4,  0x58,  0x05,  0xb8,
                    0xb3,  0x45,  0x06,  0xd0,
                    0x2c,  0x1e,  0x8f,  0xca,
                    0x3f,  0x0f,  0x02,  0xc1,
                    0xaf,  0xbd,  0x03,  0x01,
                    0x13,  0x8a,  0x6b,  0x3a,
                    0x91,  0x11,  0x41,  0x4f,
                    0x67,  0xdc,  0xea,  0x97,
                    0xf2,  0xcf,  0xce,  0xf0,
                    0xb4,  0xe6,  0x73,  0x96,
                    0xac,  0x74,  0x22,  0xe7,
                    0xad,  0x35,  0x85,  0xe2,
                    0xf9,  0x37,  0xe8,  0x1c,
                    0x75,  0xdf,  0x6e,  0x47,
                    0xf1,  0x1a,  0x71,  0x1d,
                    0x29,  0xc5,  0x89,  0x6f,
                    0xb7,  0x62,  0x0e,  0xaa,
                    0x18,  0xbe,  0x1b,  0xfc,
                    0x56,  0x3e,  0x4b,  0xc6,
                    0xd2,  0x79,  0x20,  0x9a,
                    0xdb,  0xc0,  0xfe,  0x78,
                    0xcd,  0x5a,  0xf4,  0x1f,
                    0xdd,  0xa8,  0x33,  0x88,
                    0x07,  0xc7,  0x31,  0xb1,
                    0x12,  0x10,  0x59,  0x27,
                    0x80,  0xec,  0x5f,  0x60,
                    0x51,  0x7f,  0xa9,  0x19,
                    0xb5,  0x4a,  0x0d,  0x2d,
                    0xe5,  0x7a,  0x9f,  0x93,
                    0xc9,  0x9c,  0xef,  0xa0,
                    0xe0,  0x3b,  0x4d,  0xae,
                    0x2a,  0xf5,  0xb0,  0xc8,
                    0xeb,  0xbb,  0x3c,  0x83,
                    0x53,  0x99,  0x61,  0x17,
                    0x2b,  0x04,  0x7e,  0xba,
                    0x77,  0xd6,  0x26,  0xe1,
                    0x69,  0x14,  0x63,  0x55,
                    0x21,  0x0c,  0x7d },
            // inverse of S-box type 2
            {  0x30,  0x68,  0x99,  0x1b,  0x87,
                    0xb9,  0x21,  0x78,  0x50,
                    0x39,  0xdb,  0xe1,  0x72,
                    0x09,  0x62,  0x3c,  0x3e,
                    0x7e,  0x5e,  0x8e,  0xf1,
                    0xa0,  0xcc,  0xa3,  0x2a,
                    0x1d,  0xfb,  0xb6,  0xd6,
                    0x20,  0xc4,  0x8d,  0x81,
                    0x65,  0xf5,  0x89,  0xcb,
                    0x9d,  0x77,  0xc6,  0x57,
                    0x43,  0x56,  0x17,  0xd4,
                    0x40,  0x1a,  0x4d,  0xc0,
                    0x63,  0x6c,  0xe3,  0xb7,
                    0xc8,  0x64,  0x6a,  0x53,
                    0xaa,  0x38,  0x98,  0x0c,
                    0xf4,  0x9b,  0xed,  0x7f,
                    0x22,  0x76,  0xaf,  0xdd,
                    0x3a,  0x0b,  0x58,  0x67,
                    0x88,  0x06,  0xc3,  0x35,
                    0x0d,  0x01,  0x8b,  0x8c,
                    0xc2,  0xe6,  0x5f,  0x02,
                    0x24,  0x75,  0x93,  0x66,
                    0x1e,  0xe5,  0xe2,  0x54,
                    0xd8,  0x10,  0xce,  0x7a,
                    0xe8,  0x08,  0x2c,  0x12,
                    0x97,  0x32,  0xab,  0xb4,
                    0x27,  0x0a,  0x23,  0xdf,
                    0xef,  0xca,  0xd9,  0xb8,
                    0xfa,  0xdc,  0x31,  0x6b,
                    0xd1,  0xad,  0x19,  0x49,
                    0xbd,  0x51,  0x96,  0xee,
                    0xe4,  0xa8,  0x41,  0xda,
                    0xff,  0xcd,  0x55,  0x86,
                    0x36,  0xbe,  0x61,  0x52,
                    0xf8,  0xbb,  0x0e,  0x82,
                    0x48,  0x69,  0x9a,  0xe0,
                    0x47,  0x9e,  0x5c,  0x04,
                    0x4b,  0x34,  0x15,  0x79,
                    0x26,  0xa7,  0xde,  0x29,
                    0xae,  0x92,  0xd7,  0x84,
                    0xe9,  0xd2,  0xba,  0x5d,
                    0xf3,  0xc5,  0xb0,  0xbf,
                    0xa4,  0x3b,  0x71,  0x44,
                    0x46,  0x2b,  0xfc,  0xeb,
                    0x6f,  0xd5,  0xf6,  0x14,
                    0xfe,  0x7c,  0x70,  0x5a,
                    0x7d,  0xfd,  0x2f,  0x18,
                    0x83,  0x16,  0xa5,  0x91,
                    0x1f,  0x05,  0x95,  0x74,
                    0xa9,  0xc1,  0x5b,  0x4a,
                    0x85,  0x6d,  0x13,  0x07,
                    0x4f,  0x4e,  0x45,  0xb2,
                    0x0f,  0xc9,  0x1c,  0xa6,
                    0xbc,  0xec,  0x73,  0x90,
                    0x7b,  0xcf,  0x59,  0x8f,
                    0xa1,  0xf9,  0x2d,  0xf2,
                    0xb1,  0x00,  0x94,  0x37,
                    0x9f,  0xd0,  0x2e,  0x9c,
                    0x6e,  0x28,  0x3f,  0x80,
                    0xf0,  0x3d,  0xd3,  0x25,
                    0x8a,  0xb5,  0xe7,  0x42,
                    0xb3,  0xc7,  0xea,  0xf7,
                    0x4c,  0x11,  0x33,  0x03,
                    0xa2,  0xac,  0x60 } };

    private  int[][] KRK = {
            {  0x51,  0x7c,  0xc1,  0xb7,  0x27,
                    0x22,  0x0a,  0x94,  0xfe,
                    0x13,  0xab,  0xe8,  0xfa,
                    0x9a,  0x6e,  0xe0 },
            {  0x6d,  0xb1,  0x4a,  0xcc,  0x9e,
                    0x21,  0xc8,  0x20,  0xff,
                    0x28,  0xb1,  0xd5,  0xef,
                    0x5d,  0xe2,  0xb0 },
            {  0xdb,  0x92,  0x37,  0x1d,  0x21,
                    0x26,  0xe9,  0x70,  0x03,
                    0x24,  0x97,  0x75,  0x04,
                    0xe8,  0xc9,  0x0e } };

    private  int[] m_MasterKey = new int[32];

    public ARIA(String key)
    {
        CreateMasterKey(key);
    }

    private  void DL(int[] i, int[] o) {
        int T;

        T =  (i[3] ^ i[4] ^ i[9] ^ i[14]);
        o[0] =  (i[6] ^ i[8] ^ i[13] ^ T);
        o[5] =  (i[1] ^ i[10] ^ i[15] ^ T);
        o[11] =  (i[2] ^ i[7] ^ i[12] ^ T);
        o[14] =  (i[0] ^ i[5] ^ i[11] ^ T);
        T =  (i[2] ^ i[5] ^ i[8] ^ i[15]);
        o[1] =  (i[7] ^ i[9] ^ i[12] ^ T);
        o[4] =  (i[0] ^ i[11] ^ i[14] ^ T);
        o[10] =  (i[3] ^ i[6] ^ i[13] ^ T);
        o[15] =  (i[1] ^ i[4] ^ i[10] ^ T);
        T =  (i[1] ^ i[6] ^ i[11] ^ i[12]);
        o[2] =  (i[4] ^ i[10] ^ i[15] ^ T);
        o[7] =  (i[3] ^ i[8] ^ i[13] ^ T);
        o[9] =  (i[0] ^ i[5] ^ i[14] ^ T);
        o[12] =  (i[2] ^ i[7] ^ i[9] ^ T);
        T =  (i[0] ^ i[7] ^ i[10] ^ i[13]);
        o[3] =  (i[5] ^ i[11] ^ i[14] ^ T);
        o[6] =  (i[2] ^ i[9] ^ i[12] ^ T);
        o[8] =  (i[1] ^ i[4] ^ i[15] ^ T);
        o[13] =  (i[3] ^ i[6] ^ i[8] ^ T);
    }

    private  void DL(int[] i, int iIndex, int[] o, int oIndex) {
        int T;

        T =  (i[iIndex + 3] ^ i[iIndex + 4] ^ i[iIndex + 9] ^ i[iIndex + 14]);
        o[oIndex + 0] =  (i[iIndex + 6] ^ i[iIndex + 8] ^ i[iIndex + 13] ^ T);
        o[oIndex + 5] =  (i[iIndex + 1] ^ i[iIndex + 10] ^ i[iIndex + 15] ^ T);
        o[oIndex + 11] =  (i[iIndex + 2] ^ i[iIndex + 7] ^ i[iIndex + 12] ^ T);
        o[oIndex + 14] =  (i[iIndex + 0] ^ i[iIndex + 5] ^ i[iIndex + 11] ^ T);
        T =  (i[iIndex + 2] ^ i[iIndex + 5] ^ i[iIndex + 8] ^ i[iIndex + 15]);
        o[oIndex + 1] =  (i[iIndex + 7] ^ i[iIndex + 9] ^ i[iIndex + 12] ^ T);
        o[oIndex + 4] =  (i[iIndex + 0] ^ i[iIndex + 11] ^ i[iIndex + 14] ^ T);
        o[oIndex + 10] =  (i[iIndex + 3] ^ i[iIndex + 6] ^ i[iIndex + 13] ^ T);
        o[oIndex + 15] =  (i[iIndex + 1] ^ i[iIndex + 4] ^ i[iIndex + 10] ^ T);
        T =  (i[iIndex + 1] ^ i[iIndex + 6] ^ i[iIndex + 11] ^ i[iIndex + 12]);
        o[oIndex + 2] =  (i[iIndex + 4] ^ i[iIndex + 10] ^ i[iIndex + 15] ^ T);
        o[oIndex + 7] =  (i[iIndex + 3] ^ i[iIndex + 8] ^ i[iIndex + 13] ^ T);
        o[oIndex + 9] =  (i[iIndex + 0] ^ i[iIndex + 5] ^ i[iIndex + 14] ^ T);
        o[oIndex + 12] =  (i[iIndex + 2] ^ i[iIndex + 7] ^ i[iIndex + 9] ^ T);
        T =  (i[iIndex + 0] ^ i[iIndex + 7] ^ i[iIndex + 10] ^ i[iIndex + 13]);
        o[oIndex + 3] =  (i[iIndex + 5] ^ i[iIndex + 11] ^ i[iIndex + 14] ^ T);
        o[oIndex + 6] =  (i[iIndex + 2] ^ i[iIndex + 9] ^ i[iIndex + 12] ^ T);
        o[oIndex + 8] =  (i[iIndex + 1] ^ i[iIndex + 4] ^ i[iIndex + 15] ^ T);
        o[oIndex + 13] =  (i[iIndex + 3] ^ i[iIndex + 6] ^ i[iIndex + 8] ^ T);
    }

    // Right-rotate 128 bit source string s by n bits and XOR it to target
    // string t
    private  void RotXOR(int[] s, int n, int[] t, int index) {
        int i, q;

        q = n / 8;
        n %= 8;
        for (i = 0; i < 16; i++) {
            t[index + (q + i) % 16] ^= (byte) (s[i] >> n);
            if (n != 0)
                t[index + (q + i + 1) % 16] ^= (byte) (s[i] << (8 - n));
        }
    }

    private void ConvertIntArray(byte[] b, int[] n)
    {
        for(int j = 0; j < b.length; j++ )
        {
            if(b[j] < 0)
                n[j] = b[j] + 256;
            else
                n[j] = b[j];
        }
    }

    private void ConvertIntArray(int[] n)
    {
        for(int j = 0; j < n.length; j++ )
        {
            if(n[j] < 0)
                n[j] = n[j] + 256;
            else
                n[j] = n[j];
        }
    }

    private void ConvertByteArray(int[] n, byte[] b)
    {
        for(int j = 0; j < n.length; j++ )
        {
            b[j] = (byte)n[j];
        }
    }

    // Encryption round key generation rountine
    // w0 : master key, e : encryption round keys
    private  int EncKeySetup(int[] w0, int[] e, int keyBits) {
        int i, R = (keyBits + 256) / 32, q;
        int[] t = new int[16];
        int[] w1 = new int[16];
        int[] w2 = new int[16];
        int[] w3 = new int[16];

        q = (keyBits - 128) / 64;
        for (i = 0; i < 16; i++)
        {
            t[i] = S[i % 4][ KRK[q][i] ^ w0[i]];
        }
        DL(t, w1);
        if (R == 14)
            for (i = 0; i < 8; i++)
                w1[i] ^= w0[16 + i];
        else if (R == 16)
            for (i = 0; i < 16; i++)
                w1[i] ^= w0[16 + i];

        q = (q == 2) ? 0 : (q + 1);
        for (i = 0; i < 16; i++)
            t[i] = S[(2 + i) % 4][KRK[q][i] ^ w1[i]];
        DL(t, w2);
        for (i = 0; i < 16; i++)
            w2[i] ^= w0[i];

        q = (q == 2) ? 0 : (q + 1);
        for (i = 0; i < 16; i++)
            t[i] = S[i % 4][KRK[q][i] ^ w2[i]];
        DL(t, w3);
        for (i = 0; i < 16; i++)
            w3[i] ^= w1[i];
        for (i = 0; i < 16 * (R + 1); i++)
            e[i] = 0;

        RotXOR(w0, 0, e, 0);
        RotXOR(w1, 19, e, 0);
        RotXOR(w1, 0, e, 16);
        RotXOR(w2, 19, e, 16);
        RotXOR(w2, 0, e, 32);
        RotXOR(w3, 19, e, 32);
        RotXOR(w3, 0, e, 48);
        RotXOR(w0, 19, e, 48);
        RotXOR(w0, 0, e, 64);
        RotXOR(w1, 31, e, 64);
        RotXOR(w1, 0, e, 80);
        RotXOR(w2, 31, e, 80);
        RotXOR(w2, 0, e, 96);
        RotXOR(w3, 31, e, 96);
        RotXOR(w3, 0, e, 112);
        RotXOR(w0, 31, e, 112);
        RotXOR(w0, 0, e, 128);
        RotXOR(w1, 67, e, 128);
        RotXOR(w1, 0, e, 144);
        RotXOR(w2, 67, e, 144);
        RotXOR(w2, 0, e, 160);
        RotXOR(w3, 67, e, 160);
        RotXOR(w3, 0, e, 176);
        RotXOR(w0, 67, e, 176);
        RotXOR(w0, 0, e, 192);
        RotXOR(w1, 97, e, 192);
        if (R > 12) {
            RotXOR(w1, 0, e, 208);
            RotXOR(w2, 97, e, 208);
            RotXOR(w2, 0, e, 224);
            RotXOR(w3, 97, e, 224);
        }
        if (R > 14) {
            RotXOR(w3, 0, e, 240);
            RotXOR(w0, 97, e, 240);
            RotXOR(w0, 0, e, 256);
            RotXOR(w1, 109, e, 256);
        }
        return R;
    }

    // Decryption round key generation rountine
    // w0 : maskter key, d : decryption round keys
    private  int DecKeySetup(int[] w0, int[] d, int keyBits) {
        int i, j, R;
        int[] t = new int[16];

        R = EncKeySetup(w0, d, keyBits);
        for (j = 0; j < 16; j++) {
            t[j] = d[j];
            d[j] = d[16 * R + j];
            d[16 * R + j] = t[j];
        }
        for (i = 1; i <= R / 2; i++) {
            DL(d, i * 16, t, 0);
            DL(d, (R - i) * 16, d, i * 16);
            for (j = 0; j < 16; j++)
                d[(R - i) * 16 + j] = t[j];
        }
        return R;
    }

    // Encryption and decryption rountine
    // p: plain text, e: round keys, c: ciphertext
    private  void Crypt(int[] p, int R, int[] e, int[] c) {
        int i, j;
        int index = 0;
        int[] t = new int[16];

        for (j = 0; j < 16; j++)
            c[j] = p[j];
        for (i = 0; i < R / 2; i++) {
            for (j = 0; j < 16; j++)
                t[j] = S[j % 4][e[index + j] ^ c[j]];
            DL(t, c);
            index += 16;
            for (j = 0; j < 16; j++)
                t[j] = S[(2 + j) % 4][e[index + j] ^ c[j]];
            DL(t, c);
            index += 16;
        }
        DL(c, t);
        for (j = 0; j < 16; j++)
            c[j] = (byte) (e[j] ^ t[j]);
    }

    private  void Crypt(int[] p, int pIndex, int R, int[] e, int[] c) {
        int i, j;
        int index = 0;
        int[] t = new int[16];

        for (j = 0; j < 16; j++)
            c[j] = p[pIndex + j];

        for (i = 0; i < R / 2; i++) {
            for (j = 0; j < 16; j++)
                t[j] = S[j % 4][e[index + j] ^ c[j]];
            DL(t, c);
            index += 16;
            for (j = 0; j < 16; j++)
                t[j] = S[(2 + j) % 4][e[index + j] ^ c[j]];
            DL(t, c);
            index += 16;
        }
        DL(c, t);
        for (j = 0; j < 16; j++)
            c[j] = (e[index + j] ^ t[j]);
    }

    private  String ToHex(byte[] bin_data) {
        StringBuilder result = new StringBuilder();

        if (bin_data != null && bin_data.length != 0) {

            for (int i = 0; i < bin_data.length; i++) {
                result.append(String.format("%02X", bin_data[i]));
            }
        }

        return result.toString();
    }

    private  byte[] ToHex(String strHex) {
        if (strHex == null || strHex.length() == 0) {
            return null;
        }

        byte[] ba = new byte[strHex.length() / 2];
        for (int i = 0; i < ba.length; i++) {
            ba[i] = (byte) Integer.parseInt(strHex.substring(2 * i, 2 * i + 2), 16);
        }
        return ba;
    }

    public  Boolean CreateMasterKey(String key) {
        int nSize = key.length();

        if (nSize > 32) {
            return false;
        }

        byte[] StrByte;
        try {
            StrByte = key.getBytes("EUC-KR");

            ConvertIntArray(StrByte, m_MasterKey);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return true;
    }

    public String Encrypt(String input) {
        String strEncrypt = "";
        int[] rk = new int[16 * 17];
        int[] c = new int[16];
        int[] p = new int[256];

        int r = EncKeySetup(m_MasterKey, rk, 256);

        ConvertIntArray(rk);
        int cnt = 0;

        try {
            byte[] StrByte = input.getBytes("EUC-KR");

            int[] sbyte = new int[StrByte.length];
            ConvertIntArray(StrByte, sbyte);

            System.arraycopy(sbyte, 0, p, 0, sbyte.length);

            cnt = (int) Math.ceil((double)( (double)sbyte.length / (double)16.0));

        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        }

        for (int i = 0; i < cnt; i++) {
            Arrays.fill(c, (byte) 0x00);
            Crypt(p, (i * 16), r, rk, c);
            byte[] b = new byte[c.length];
            ConvertByteArray(c, b);
            strEncrypt += ToHex(b);
        }

        return strEncrypt;
    }

    public  String Decrypt(String input) {
        int[] rk = new int[16 * 17];
        int[] c = new int[16];
        byte[] s = new byte[16];
        String strDecrypt = "";

        byte[] b = ToHex(input);
        int[] n = new int[b.length];
        Arrays.fill(n, 0);
        ConvertIntArray(b, n);

        int r = DecKeySetup(m_MasterKey, rk, 256);
        ConvertIntArray(rk);

        int cnt = (int) Math.ceil((double) input.length() / 32);

        ByteBuffer buffer = ByteBuffer.allocate(cnt * 16);

        for (int i = 0; i < cnt; i++) {
            Arrays.fill(c, 0);
            Arrays.fill(s, (byte) 0x00);
            Crypt(n, (i * 16), r, rk, c);

            ConvertByteArray(c, s);

            buffer.put(s);
        }

        try {
            strDecrypt = new String(buffer.array(), "EUC-KR").replace("\0", "");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return strDecrypt;
    }
}