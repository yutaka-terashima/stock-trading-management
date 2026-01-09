package com.example.stock_trading_management; 

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
        return """
            <h2>株式銘柄一覧</h2>
            <p>株式銘柄一覧が選択されました。</p>
            <br>
            <a href="/">メニューに戻る</a>
            """;
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