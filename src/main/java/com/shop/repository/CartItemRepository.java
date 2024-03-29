package com.shop.repository;


import com.shop.dto.CartDetailDto;
import com.shop.entity.CartItem;
import com.shop.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByCartIdAndItemId(Long cartId, Long itemId);

    @Query("select new com.shop.dto.CartDetailDto(ci.id, i.itemNm, i.price, ci.count, im.imgUrl) " +
            "from CartItem ci, ItemImg im " +
            "join ci.item i " +
            "where ci.cart.id = :cartId " +
            "and im.item.id = ci.item.id " +
            "and im.repimgYn = 'Y' " +
            "order by ci.regTime desc"
    )
    List<CartDetailDto> findCartDetailDtoList(@Param("cartId") Long cartId);
    /*
        SELECT
            cart_item.cart_item_id,
            item.item_Nm,
            item.price,
            cart_item.count,
            item_img.img_url
        FROM cart_item
        JOIN item
            ON cart_item.item_id = item.item_id
        JOIN item_img
            ON item.item_id = item_img.item_id
        where
            item_img.repimg_yn='Y' and cart_item.cart_id=40
            order by cart_item.reg_time desc
     */
}