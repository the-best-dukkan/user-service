package com.tbd.user_service.controller;

import com.tbd.user_service.dto.TbdAddressDTO;
import com.tbd.user_service.exception.PageSizeLimitExceedException;
import com.tbd.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/users/address")
@RestController
@RequiredArgsConstructor
public class AddressController {

    private final UserService userService;

    @PostMapping("/add-address")
    public ResponseEntity<TbdAddressDTO> addAddress(@RequestBody @Valid TbdAddressDTO tbdAddressDTO) {
        return new ResponseEntity<>(userService.addAddress(tbdAddressDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<TbdAddressDTO>> getAddresses(@PageableDefault(size = 25, sort = {"modifiedDate"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return new ResponseEntity<>(userService.getAddresses(pageable), HttpStatus.OK);
    }
}
