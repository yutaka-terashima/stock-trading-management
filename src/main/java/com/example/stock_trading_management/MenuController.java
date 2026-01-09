package com.example.stock_trading_management; 

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuController {

    @GetMapping("/")
    public String index() {
        return """
            <h1>株式取引管理システム</h1>
            <p>操作するメニューを選んでください。</p>
            <ul>
                <li><a href="/printStocks">A: 株式マスタ一覧表示</a></li>
                <li><a href="/registrationNewStock">B: 銘柄マスタ新規登録</a></li>
                <li><a href="/registrationTraded">C: 取引入力</a></li>
                <li>Q: アプリケーションを終了する (ブラウザを閉じればOK)</li>
            </ul>
            """;
    }

    @GetMapping("/printStocks")
    public String printStocks() {
        Path path = Path.of("stock-trading-management\\stock.csv");
        List<String> stocks = new ArrayList();

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            reader.readLine();

            String line;
            while((line = reader.readLine()) != null) {
                String[] stock = line.split(",");
                if (stock.length != 4) {
                    throw new IllegalArgumentException("ファイルの列数が異なっています");
                }

                stocks.add(stock[0] + "," + stock[1] + "," + stock[2] + "," + stock[3]);
            }
        } catch (NoSuchFileException var7) {
            System.out.println("ファイルが見つかりません");
        } catch (IOException var8) {
            System.out.println("dataが読み込めません");
        }

        // 2. HTMLの表（table）を組み立てる
        StringBuilder html = new StringBuilder();
        html.append("<h2>株式銘柄一覧</h2>");
        html.append("<table border='1' style='border-collapse: collapse;'>");
        html.append("<tr><th>銘柄コード</th><th>銘柄名</th><th>市場</th></tr>"); // ヘッダー

        for (String stock : stocks) {
            html.append("<tr>");
            String[] columns = stock.split(",");
            for (String column : columns) {
                html.append("<td>").append(column).append("</td>");
            }
            html.append("</tr>");
        }
        
        html.append("</table>");
        html.append("<br><a href='/'>メニューに戻る</a>");

        return html.toString();
    }

    @GetMapping("/registrationTraded")
    public String registrationTraded() {
        return """
            <h2>「取引入力」が選択されました</h2>
            <p>取引情報を入力します</p>
            <br>
            <a href="/">メニューに戻る</a>
            """;
    }

    @GetMapping("/registrationNewStock")
    public String registrationNewStock() {
        return """
            <h2>「銘柄マスタ新規登録」が選択されました</h2>
            <p>新規株式銘柄マスタを登録します</p>
            <br>
            <a href="/">メニューに戻る</a>
            """;
    }
}