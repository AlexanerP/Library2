package com.epam.library.service.impl;

import com.epam.library.entity.Status;
import com.epam.library.service.StatusService;

import java.util.ArrayList;
import java.util.List;

public class UserStatusServiceImpl implements StatusService {
    @Override
    public List<Status> getUserStatus() {
        List<Status> statuses = new ArrayList<>();
        System.out.println("UserStatusServiceImpl");
        statuses.add(new Status(1, "Active"));
        statuses.add(new Status(2, "Block"));
        statuses.add(new Status(3, "Delete"));
        return statuses;
    }
}
