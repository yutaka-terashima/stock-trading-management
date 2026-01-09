package com.example.stock_trading_management; 

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuController {

    @GetMapping("/")
    public String index() {
        // ブラウザに表示される内容です
        return """
            <h1>株式取引管理システム</h1>
            <p>操作するメニューを選んでください。</p>
            <ul>
                <li>A: 株式マスタ一覧表示</li>
                <li>B: 銘柄マスタ新規登録</li>
                <li>C: 取引入力</li>
                <li>Q: アプリケーションを終了する</li>
            </ul>
            """;
    }
}