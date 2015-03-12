package com.example;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * データストア（この場合はRDBMS）に対するItemエンティティの操作を提供するリポジトリクラス
 * 基底のインタフェースを継承するのみで、CRUDの基本操作を行える。
 * 複雑なクエリを発行したい場合は、このクラスに実装する。
 */
public interface ItemRepository extends JpaRepository<Item, Integer>{

}
