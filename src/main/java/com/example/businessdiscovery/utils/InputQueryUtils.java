package com.example.businessdiscovery.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputQueryUtils {

    /*
     * ターミナル経由でユーザーからの文字列を返します。
     */
    public static String getInputQuery() {
        return getInputQuery("");
    }

    /*
     * ターミナル経由でユーザーからの文字列を返します。
     */
    public static String getInputQuery(String defaultQuery) {
        try {
            String inputQuery = "";

            System.out.print("Please enter a short lived user access token: ");
            BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
            inputQuery = bReader.readLine();

            if (inputQuery.length() < 1) {
                // 何も入力しない場合のデフォルト。
                inputQuery = defaultQuery;
            }

            return inputQuery;

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

}
