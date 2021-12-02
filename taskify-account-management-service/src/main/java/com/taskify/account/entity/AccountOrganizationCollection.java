package com.taskify.account.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "account_organization_collection")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountOrganizationCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_account_id")
    private Account account;

    @Column(name = "organization_id")
    private Long organizationId;
}
