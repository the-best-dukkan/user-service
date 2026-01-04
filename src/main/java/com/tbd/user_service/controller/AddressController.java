package com.tbd.user_service.controller;

import com.tbd.common.validation.groups.OnCreate;
import com.tbd.common.validation.groups.OnUpdate;
import com.tbd.proto.user_service.TbdAddressPageProto;
import com.tbd.proto.user_service.TbdAddressProto;
import com.tbd.user_service.dto.TbdAddressDTO;
import com.tbd.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/users/address")
@RestController
@RequiredArgsConstructor
public class AddressController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<TbdAddressDTO> addAddress(@RequestBody @Validated(OnCreate.class) TbdAddressDTO tbdAddressDTO) {
        return new ResponseEntity<>(userService.addAddress(tbdAddressDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<TbdAddressPageProto> getAddresses(@PageableDefault(size = 25, sort = {"modifiedDate"}, direction = Sort.Direction.DESC) Pageable pageable) {
        TbdAddressPageProto addresses = userService.getAddresses(pageable);
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TbdAddressProto> getAddress(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getAddressById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TbdAddressDTO> updateAddress(@PathVariable Long id, @RequestBody @Validated({OnCreate.class, OnUpdate.class}) TbdAddressDTO tbdAddressDTO) {

        return ResponseEntity.ok(userService.updateAddress(id, tbdAddressDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TbdAddressDTO> partialUpdateAddress(@PathVariable Long id, @RequestBody @Validated(OnUpdate.class) TbdAddressDTO tbdAddressDTO) {
        return ResponseEntity.ok(userService.partialUpdateAddress(id, tbdAddressDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        userService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }
}
