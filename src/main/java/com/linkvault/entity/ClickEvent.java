package com.linkvault.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "click_events")
@Getter
@Setter
public class ClickEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "short_url_id", nullable = false)
    private ShortUrl shortUrl;

    @Column(name = "accessed_at", nullable = false, updatable = false)
    private Instant accessedAt;

    @Column(name = "user_agent", length = 512)
    private String userAgent;

    @PrePersist
    protected void onCreate() {
        if (accessedAt == null) {
            accessedAt = Instant.now();
        }
    }
}