package com.taskify.organization.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "organization_account_collection")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationAccountCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_organization_id")
    private Organization organization;

    @Column(name = "account_id")
    private Long accountId;
}
