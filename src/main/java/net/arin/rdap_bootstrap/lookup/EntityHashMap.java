/*
 * Copyright (C) 2013-2015 American Registry for Internet Numbers (ARIN)
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR
 * IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 *
 */
package net.arin.rdap_bootstrap.lookup;

import net.arin.rdap_bootstrap.lookup.Lookup;
import net.arin.rdap_bootstrap.lookup.Store;
import net.arin.rdap_bootstrap.lookup.Store.Entity;
import net.arin.rdap_bootstrap.service.Bootstrap;
import net.arin.rdap_bootstrap.service.ResourceFiles;
import net.arin.rdap_bootstrap.service.ResourceFiles.BootFiles;
import net.arin.rdap_bootstrap.service.Rfc7484;

import java.util.HashMap;

/**
 * @version $Rev$, $Date$
 */
public class EntityHashMap implements Lookup.Entity, Store.Entity
{
    private HashMap<String,ServiceUrls> allocations = new HashMap<String, ServiceUrls>(  );


    @Override
    public void store( String entry, ServiceUrls serviceUrls )
    {
        allocations.put( entry, serviceUrls );
    }

    @Override
    public ServiceUrls getServiceUrlsForEntity( String entity )
    {
        return allocations.get( entity );
    }

    @Override
    public Entity createLoadContext()
    {
        return new EntityHashMap();
    }

    @Override
    public void loadWithContext( Entity entity, boolean success )
    {
        if( success )
        {
            allocations = ((EntityHashMap)entity).allocations;
        }
    }
}
