# DNS

## Name Server

Its generic name of any server involved in the process of domain name resolution

### Authoritative Name Server

Its server which has entry of website and its IP address.

```bash
www.my-website.com A a.b.c.d
```

![](./images/Overview.svg)

## Website configuration

![](./images/Configure-Website.svg)

## Domain Name resolution Flow

Steps:

1. when user type `www.my-website.com`, its IP address is looked in Browser cache, its being used if found.
2. Look for IP address inside OS cache or Home Rounter cache. Use it if found.
3. Request is made to ISP to resolve the `www.my-website.com` name which starts the domain name resolution process.
4. It first talks to `Root Name Server or Root Server` (which is 13 spread across the world).
5. `Root Server` looks for the `TLD` which is `.com` and returns the IP address of `TLD (.com) Name Server`.
6. `TLD (.com) Name Server` looks for `Name Server or Authoritative Name Server` entry stored (always). Return it

![](./images/Website-IP-Flow.svg)

## Glue Records

Glue records returns as **additional Records** section.

### Why do we need Glue records at first place?

Consider scenario where Domain provider like Godaddy has website where user wants to configure their websites.
In this case, domain-registrar website address is (for example)

```bash
www.domain-registrar.com
```

NameServers are:

```bash
ns1.domain-registrar.com
ns2.domain-registrar.com
```

When user tries to accesss `www.domain-registrar.com`, following happens:

1. Goes to Root Name server, Which identifies `.com` as domain and sends to `TLD (.com)`.
2. `TLD (.com)` checks for `Name Servers` of `www.domain-registrar.com` which is `ns2.domain-registrar.com`. Since it doesn't have IP address of `ns2.domain-registrar.com`, it starts the DNS resolution process again.
3. :exclamation::exclamation: Infinite Loop :exclamation::exclamation:

![](./images/Glue-Records.svg)

# References

1. [IBM Glue Record](https://www.ibm.com/think/topics/glue-records#:~:text=Glue%20records%20appear%20in%20the,my%20glue%20records%20are%20correct%3F)
